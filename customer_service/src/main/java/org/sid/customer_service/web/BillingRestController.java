package org.sid.customer_service.web;


import org.sid.customer_service.entities.Customer;
import org.sid.customer_service.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillingRestController {
    private CustomerRepository customerRepository;

    public BillingRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(path ="/findCustomer")
    public List<Customer> findProduct(@RequestParam(name="page",defaultValue = "0") int page,
                                      @RequestParam(name="size",defaultValue = "10")int size,
                                      @RequestParam(name="keyword",defaultValue = "")String key){
        Page<Customer> pageCustomer = customerRepository.findByNameContains(key, PageRequest.of(page, size));
        return pageCustomer.getContent();
    }




}

