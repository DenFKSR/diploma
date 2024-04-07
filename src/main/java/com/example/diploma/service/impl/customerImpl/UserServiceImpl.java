//package com.example.diploma.service.impl.customerImpl;
//
//import com.example.diploma.model.db.entity.Customer;
//import com.example.diploma.model.db.entity.UserImpl;
//import com.example.diploma.model.db.repository.CustomerRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserDetailsService {
//
//    private final CustomerRepo customerRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Customer> customerOptional = customerRepo.findByEmail(username);
//        return customerOptional.map(UserImpl::new)
//                .orElseThrow(() -> new UsernameNotFoundException(""));
//    }
//}
