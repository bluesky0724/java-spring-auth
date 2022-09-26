//package com.example.demo.service;
//
//import com.example.demo.data.User;
//import com.example.demo.exception.EntityNotFoundException;
//import com.example.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.transaction.annotation.Transactional;
//
//public class UserDetailsServiceImp {
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username" + email));
//        return UserDetailsImp
//    }
//}
