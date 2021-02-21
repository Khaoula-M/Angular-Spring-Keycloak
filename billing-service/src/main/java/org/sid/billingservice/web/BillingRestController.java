package org.sid.billingservice.web;

import lombok.Data;
import org.sid.billingservice.entites.Bill;
import org.sid.billingservice.entites.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepsitory;
import org.sid.billingservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BillingRestController {
    private BillRepsitory billRepsitory;
    private ProductRepository productRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepsitory billRepsitory, ProductRepository productRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepsitory = billRepsitory;
        this.productRepository = productRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path ="/fullBill/{id}")
    public Bill getBill(@PathVariable(name="id") Long id){
    Bill bill = billRepsitory.findById(id).get();
        Customer customer= customerRestClient.getCustomerById(bill.getCustomerID());
    bill.setCustomer(customer);
    bill.getProductItems().forEach(pi->{
        Product product=productItemRestClient.getProductById(pi.getProductID());
        pi.setProduct(product);
    });
    return bill;

    }

    @GetMapping(path ="/fullBills")
    public List<Bill> getBills(){
        billRepsitory.findAll().forEach(b->{
            Customer customer= customerRestClient.getCustomerById(b.getCustomerID());
            b.setCustomer(customer);
            b.getProductItems().forEach(pi->{
                Product product=productItemRestClient.getProductById(pi.getProductID());
                pi.setProduct(product);
            });
        });
        return billRepsitory.findAll();
    }

    @PostMapping(path ="/generateBill")
    public void generateBill(@RequestBody BillForm billForm){
        Bill bill = new Bill();
        bill.setCustomerID(billForm.getCustomerId());
        bill.setBillingDate(new Date());
        bill.setProductItems(billForm.getProductItems());
        billRepsitory.save(bill);
    }


}

@Data
class BillForm{
    private String name;
    private Long customerId;
    private List<ProductItem> productItems;
}

