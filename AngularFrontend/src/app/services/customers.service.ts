import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { KeycloakSecurityService } from './keycloak-security.service';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  public host:string="http://localhost:8081"

  constructor(private http: HttpClient, private securityService: KeycloakSecurityService) { }

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

  getSearchedCustomers(value: string) {
    return this.http.get(this.host+"/findCustomer?keyword="+value);
  }
}
