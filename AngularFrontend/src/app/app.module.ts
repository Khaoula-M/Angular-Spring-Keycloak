// @ts-ignore
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER, DoBootstrap } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KeycloakSecurityService } from './services/keycloak-security.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RequestInterceptorService } from './services/request-interceptor.service';
import { CustomersComponent } from './customers/customers.component';
import { ProductsComponent } from './products/products.component';
import { BillingComponent } from './billing/billing.component';
import {FormsModule} from "@angular/forms";
import { AddProductComponent } from './add-product/add-product.component';


// export function keycloakFactory(keycloakSecurityService: KeycloakSecurityService) {
//   return () => keycloakSecurityService.init();
// }
const keycloakSecurityService = new KeycloakSecurityService();

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    ProductsComponent,
    BillingComponent,
    AddProductComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule

  ],
  providers: [
    // { provide: APP_INITIALIZER, deps: [KeycloakSecurityService], useFactory: keycloakFactory, multi: true },
    { provide: KeycloakSecurityService, useValue: keycloakSecurityService },
    { provide: HTTP_INTERCEPTORS, useClass: RequestInterceptorService, multi: true }

  ],
  entryComponents: [AppComponent]
  // bootstrap: [AppComponent]
})
export class AppModule implements DoBootstrap {
  // @ts-ignore
  ngDoBootstrap(appRef: import("@angular/core").ApplicationRef): void {
    keycloakSecurityService.init().then(data => {
      console.log('authenticated + token :', data);
      appRef.bootstrap(AppComponent);

    }).catch(err => {
      console.error('err', err);
    });
  }
}
