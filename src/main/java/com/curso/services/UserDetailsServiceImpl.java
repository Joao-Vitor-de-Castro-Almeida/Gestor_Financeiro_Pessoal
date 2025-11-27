package com.curso.services;

import com.curso.domains.Cliente;
import com.curso.repositories.ClienteRepository;
import com.curso.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteRepository clienteRepo;

    public UserDetailsServiceImpl(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Cliente> user = clienteRepo.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserSS(user.orElse(null));
    }
}
