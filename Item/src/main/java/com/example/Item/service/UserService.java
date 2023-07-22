package com.example.Item.service;

import com.example.Item.model.User;
import com.example.Item.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         Optional<User> record = repository.findByUsername(username);

        if (record.isEmpty()) {
            throw new UsernameNotFoundException("User not found - " + username);
        }

        final User user = record.get();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );

    }

    public User save(final User user) {
        return repository.save(user);
    }


}
