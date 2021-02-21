import { Component, OnInit } from '@angular/core';
import {CustomersService} from "../services/customers.service";

export interface Customer{
  id: number;
  name: string;
}
@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  errorMessage: string = null;
  customers: Object;

  constructor(private customersService: CustomersService) { }
  ngOnInit(): void {
    this.SearchCustomer('')
  }

  SearchCustomer(value: string) {
    console.log("GRow up")
    this.customersService.getSearchedCustomers(value)
      .subscribe(data=>{
        this.customers=data;
      },err=>{
        console.log(err);
      })
  }
}
