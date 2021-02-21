package org.sid.inventory_service;

import org.sid.inventory_service.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.sid.inventory_service.repository.ProductRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(null,"Laptop",7000,12,false));
            productRepository.save(new Product(null,"Mobile",800,12,false));
            productRepository.save(new Product(null,"Imprimante",2029,12,false));
            productRepository.save(new Product(null,"Gaming Laptop",8999,3,false));
            productRepository.save(new Product(null,"Gaming Keyboard",399,20,false));
            productRepository.save(new Product(null,"Télévision 4K",6999,10,false));
            productRepository.save(new Product(null,"Monitor",1999,20,false));
            productRepository.save(new Product(null,"Pack Gaming",729,15,false));
            //productRepository.save(new Product(null,"AirPods",209,11,false));
            productRepository.findAll().forEach(p->{
                System.out.println(p.toString());
            });

        };
    }

}
