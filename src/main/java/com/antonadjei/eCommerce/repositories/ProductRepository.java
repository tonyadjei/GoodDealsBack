package com.antonadjei.eCommerce.repositories;

import com.antonadjei.eCommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}