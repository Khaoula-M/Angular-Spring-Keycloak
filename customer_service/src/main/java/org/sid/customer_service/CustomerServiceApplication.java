package org.sid.customer_service;

import org.sid.customer_service.entities.Customer;
import org.sid.customer_service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
        return  args -> {
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.save(new Customer( null,"Khaoula","hamid.hamdoun@gmail.com"));
            customerRepository.save(new Customer( null,"Bayed","souad.hamdoun@gmail.com"));
            customerRepository.save(new Customer( null,"Bahmad","bahmad.hamdoun@gmail.com"));
            customerRepository.save(new Customer( null,"Saiid","simo$wisal@gmail.com"));
            customerRepository.save(new Customer( null,"Loubna","your.phone@gmail.com"));
            customerRepository.save(new Customer( null,"khouloud","khouloud.ma@gmail.com"));
            customerRepository.save(new Customer( null,"Sara","khanza.com@gmail.com"));
            customerRepository.save(new Customer( null,"Mehdi","food.ma@gmail.com"));
            customerRepository.findAll().forEach(c->{
                System.out.println(c.toString());
            });
        };

    }
}
