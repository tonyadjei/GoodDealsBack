package com.antonadjei.eCommerce.controllers;

import com.antonadjei.eCommerce.dtos.registration.AuthenticationRequest;
import com.antonadjei.eCommerce.dtos.registration.AuthenticationResponse;
import com.antonadjei.eCommerce.dtos.registration.RegisterRequest;
import com.antonadjei.eCommerce.dtos.user.UserResponseDTO;
import com.antonadjei.eCommerce.services.UserService;
import com.antonadjei.eCommerce.services.auth.JWTService;
import com.antonadjei.eCommerce.services.auth.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserService userService;
    private final TokenBlacklistService tokenBlacklistService;
    private final JWTService jwtService;


    /**
     * Registers a new user based on the provided registration request.
     *
     * @param request The registration request containing user details
     * @return ResponseEntity containing an authentication response
     * @author Fethi Benseddik
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid
            @RequestBody RegisterRequest request
    ) {
        UserResponseDTO response = new UserResponseDTO();
        response = UserResponseDTO.toUserResponseDTO(userService.register(request));
        log.info("REST request to register user {}", request.getUserName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Authenticates a user based on the provided authentication request.
     *
     * @param request The authentication request containing user credentials
     * @return ResponseEntity containing an authentication response
     * @author Fethi Benseddik
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(
            @Valid
            @RequestBody AuthenticationRequest request
    ) {
        log.info("REST request to authenticate user {}", request.getUserName());
        return ResponseEntity.ok(userService.authenticate(request));
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        tokenBlacklistService.blacklistToken(request);
        return ResponseEntity.ok().body("Logout Success!");
    }
}
