package com.antonadjei.eCommerce.repositories;

import com.antonadjei.eCommerce.models.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {
    Optional<TokenBlacklist> findByValue(String value);
}