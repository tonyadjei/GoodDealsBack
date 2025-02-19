package com.antonadjei.eCommerce.repositories;

import com.antonadjei.eCommerce.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryRole extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
