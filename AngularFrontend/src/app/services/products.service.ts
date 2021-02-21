import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {KeycloakSecurityService} from "./keycloak-security.service";
import {Product} from "../products/products.component";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  public host:string="http://localhost:8082"
  constructor(private http: HttpClient, private securityService: KeycloakSecurityService) { }

  public getProducts() {
    return this.http
      .get<Product[]>(this.host+'/products').pipe(
        catchError(this.handleError),
        // tap(data => console.log('data', data))
      );
  }

  private handleError(errorRes: HttpErrorResponse) {
    console.log('errorRes', errorRes)
    let errorMessage = 'an unknown error occured';
    if (!errorRes.error || !errorRes.error.error || !errorRes.error.message) {
      return throwError(errorMessage);
    }
    switch (errorRes.error.message) {
      case 'Forbidden':
        errorMessage = 'Vous n\'avez pas les droits ! ou vous n\'êtes pas logger';
        break;
      case 'EMAIL_NOT_FOUND':
        errorMessage = 'this Email does not exist';
        break;
      case 'INVALID_PASSWORD':
        errorMessage = 'this password is not correct';
        break;

    }
    return throwError(errorMessage);
  }

  getSearchedProducts(key) {
    return this.http.get(this.host+"/findProduct?keyword="+key);
  }

  addProduct(form) {
    console.log(" 11 "+form)
    return this.http.post(this.host+"/addProduct",form);
  }

  select(p: Product):Observable<Product> {
    p.selected = !p.selected;
    return this.http.put<Product>(this.host + "/products/" + p.id, p);
  }
}
