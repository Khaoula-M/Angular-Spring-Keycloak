package org.sid.inventory_service.repository;

import org.sid.inventory_service.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {
    public Page<Product> findByNameContains(String mc, Pageable pageable);
    public Page<Product> findBySelectedIsTrue(Pageable pageable);
}
