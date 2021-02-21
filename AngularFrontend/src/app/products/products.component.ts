import { Component, OnInit } from '@angular/core';
import {ProductsService} from "../services/products.service";

export interface Product{
  id: number;
  name: string;
  selected : boolean;
}
@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  errorMessage: string = null;
  products: Object;
  selectedProducts= [];

  constructor(private productsService: ProductsService) { }

  ngOnInit(): void {
    this.SearchProduct('')
  }

  SearchProduct(key){
    console.log("whaat "+key)
    this.productsService.getSearchedProducts(key)
      .subscribe(data=>{
        this.products=data;
        console.log('bojii '+this.products)
      },err=>{
        console.log(err);
      })
  }
  onSelect(p: Product) {
    console.log("OKKKKK "+p.name)
    this.selectedProducts.push(p)
    console.log(this.selectedProducts)
    this.productsService.select(p)
      .subscribe(data=>{
        p.selected=data.selected;
      })
    console.log("OKK "+p)
  }

  addToBill() {
    return this.selectedProducts
  }
}
