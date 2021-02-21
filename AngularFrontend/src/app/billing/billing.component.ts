import { Component, OnInit } from '@angular/core';
import {BillingService} from "../services/billing.service";

export interface Bill{
  id: number;
  name: string;
  //email: string;
}

@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.scss']
})
export class BillingComponent implements OnInit {

  errorMessage: string = null;
  bills: any;

  constructor(private billingService: BillingService) { }

  ngOnInit(): void {
    this.billingService.getBills().subscribe(
      data => {
        this.bills = data;
      },
      err => {
        this.errorMessage = err;
        console.log('errorrr ! ', err)
      }
    );

  }

}
