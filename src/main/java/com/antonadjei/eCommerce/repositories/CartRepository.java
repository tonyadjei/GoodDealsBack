package com.antonadjei.eCommerce.repositories;

import com.antonadjei.eCommerce.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}