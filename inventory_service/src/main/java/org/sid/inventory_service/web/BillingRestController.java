package org.sid.inventory_service.web;


import lombok.Data;
import org.sid.inventory_service.entities.Product;
import org.sid.inventory_service.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BillingRestController {
    private ProductRepository productRepository;

    public BillingRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path ="/findProduct")
    public List<Product> findProduct(@RequestParam(name="page",defaultValue = "0") int page,
                                     @RequestParam(name="size",defaultValue = "10")int size,
                                     @RequestParam(name="keyword",defaultValue = "")String key){
        Page<Product> pageProduct = productRepository.findByNameContains(key, PageRequest.of(page, size));
        return pageProduct.getContent();
    }

    @GetMapping(path ="/selectedProduct")
    public List<Product> selectedProduct(@RequestParam(name="page",defaultValue = "0") int page,
                                     @RequestParam(name="size",defaultValue = "10")int size){
        Page<Product> pageProduct = productRepository.findBySelectedIsTrue(PageRequest.of(page, size));
        return pageProduct.getContent();
    }

    @PostMapping(path ="/addProduct")
    public void addProduct(@RequestBody ProductForm productForm){
        //Product product = new Product(null,productForm.getName(),productForm.getPrice(),productForm.getQuantity());
        Product product = new Product(null,"boji",13,5,false);
        productRepository.save(product);
    }

}

@Data
class ProductForm{
    private String name;
    private double price;
    private double quantity;
}
