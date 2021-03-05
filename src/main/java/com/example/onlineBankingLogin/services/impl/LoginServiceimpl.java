package com.example.onlineBankingLogin.services.impl;

import com.example.onlineBankingLogin.entity.Users;
import com.example.onlineBankingLogin.repository.UserRepository;
import com.example.onlineBankingLogin.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginServiceimpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.getUserByEmail(email);
        return new User(users.getEmail(), users.getPassword(), new ArrayList<>());
    }
}
