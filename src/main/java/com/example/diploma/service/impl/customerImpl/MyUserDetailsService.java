package com.example.diploma.service.impl.customerImpl;

import com.example.diploma.config.MyUserDetails;
import com.example.diploma.model.db.entity.Customer;
import com.example.diploma.model.db.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepo repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> user = repository.findByEmail(email);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }
}
