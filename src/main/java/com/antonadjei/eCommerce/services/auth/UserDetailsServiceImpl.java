package com.antonadjei.eCommerce.services.auth;


import com.antonadjei.eCommerce.exceptions.UserNotFoundException;
import com.antonadjei.eCommerce.repositories.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RepositoryUser userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(
                "User with name: %s was not found.", username
        )));
    }

    public UserDetails loadUserByID(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(
                "User with id: %d was not found.", id
        )));    }
}
