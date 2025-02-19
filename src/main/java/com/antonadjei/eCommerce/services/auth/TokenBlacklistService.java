package com.antonadjei.eCommerce.services.auth;



import com.antonadjei.eCommerce.models.TokenBlacklist;
import com.antonadjei.eCommerce.repositories.TokenBlacklistRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {
    private final TokenBlacklistRepository tokenBlacklistRepository;

    public void addToBlacklist(String tokenValue) {
        TokenBlacklist token = new TokenBlacklist();
        token.setValue(tokenValue);
        tokenBlacklistRepository.save(token);
    }

    public boolean isBlacklisted(String tokenValue) {
        Optional<TokenBlacklist> token = tokenBlacklistRepository.findByValue(tokenValue);
        if (token.isPresent()) {
            return true;
        }
        return false;
    }

    public void blacklistToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String tokenValue = authHeader.substring(7);
        addToBlacklist(tokenValue);
    }
}
