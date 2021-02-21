import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import {CustomersComponent} from "./customers/customers.component";
import {ProductsComponent} from "./products/products.component";
import {BillingComponent} from "./billing/billing.component";
import {AddProductComponent} from "./add-product/add-product.component";


const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: '', component: AppComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'addProduct', component: AddProductComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'bills', component: BillingComponent },
  { path: '**', redirectTo: '/' }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
