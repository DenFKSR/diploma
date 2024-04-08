//package com.example.diploma.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.servlet.http.HttpServletResponse;
//
//@EnableWebSecurity
//@Configuration
//class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        AuthenticationEntryPoint authenticationEntryPoint = (request, response, authException) -> {
//            response.setHeader("WWW-Authenticate", "FormBased");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//        };
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/rent").hasAuthority("USER")
//                .antMatchers("/customer/new_user").permitAll()
//                .and()
////                .formLogin().loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
////                .permitAll()
////                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//
