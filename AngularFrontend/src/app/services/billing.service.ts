import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {KeycloakSecurityService} from "./keycloak-security.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Bill} from "../billing/billing.component";

@Injectable({
  providedIn: 'root'
})
export class BillingService {
  constructor(private http: HttpClient, private securityService: KeycloakSecurityService) { }


  public getBills() {
    return this.http
      .get<Bill[]>('http://localhost:8083/fullBills').pipe(
        catchError(this.handleError),
        // tap(data => console.log('data', data))
      );
  }

  //,{headers:new HttpHeaders({Authorization:'Bearer '+this.securityService.keycloak.token})}

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
}
