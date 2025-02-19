package com.antonadjei.eCommerce.repositories;

import com.antonadjei.eCommerce.models.Cart_Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cart_ProductsRepository extends JpaRepository<Cart_Products, Long> {
}