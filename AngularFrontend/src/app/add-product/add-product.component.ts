import { Component, OnInit } from '@angular/core';
import {ProductsService} from "../services/products.service";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {

  constructor(public productsService:ProductsService) { }


  ngOnInit(): void {

  }

  OnAddProduct(form){
    console.log('foorm '+form)
    this.productsService.addProduct(form)
      .subscribe(data=>{
        alert("produit ajouté avec succès !!")
      },err=>{
        console.log(err);
      })
    console.log('formitaa '+form)
  }
}
