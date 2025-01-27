package org.sid.billingservice;

import org.sid.billingservice.entites.Bill;
import org.sid.billingservice.entites.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepsitory;
import org.sid.billingservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

   @Bean
    CommandLineRunner start(BillRepsitory billRepsitory,
                            ProductRepository productRepository,
                            CustomerRestClient customerRestClient,
                            ProductItemRestClient productItemRestClient){
        return args->{
            Customer customer =customerRestClient.getCustomerById(1L);
            //Customer customer2 =customerRestClient.getCustomerById(2L);
            Bill bill =billRepsitory.save(new Bill(null, new Date(),null,customer.getId(),null));
            //Bill bill2 =billRepsitory.save(new Bill(null, new Date(),null,customer2.getId(),customer2));

            PagedModel<Product> productPagedModel =productItemRestClient.pageProducts();
            productPagedModel.forEach(p->{
                ProductItem productItem= new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(1+new Random().nextInt(100));
                productItem.setBill(bill);
                productItem.setProductID(p.getId());
                productRepository.save(productItem);

            });
            System.out.println("---------------------");
            System.out.println(customer.getName());
            System.out.println(customer.getEmail());

        };

    }
}
