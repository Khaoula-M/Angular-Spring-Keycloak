package org.sid.billingservice.repository;

import org.sid.billingservice.entites.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface BillRepsitory extends JpaRepository<Bill,Long> {
}
