//package com.example.diploma.model.db.entity;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.Collections;
//
//public class UserImpl extends org.springframework.security.core.userdetails.User {
//
//    private final long id;
//
//    public UserImpl(Customer customer) {
//        super(customer.getEmail(), customer.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
//        this.id = customer.getId();
//    }
//
//    @Override
//    public boolean equals(Object rhs) {
//        return super.equals(rhs);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//}
