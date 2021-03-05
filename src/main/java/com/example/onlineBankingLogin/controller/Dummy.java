package com.example.onlineBankingLogin.controller;

import com.example.onlineBankingLogin.dto.LoginRequestDTO;
import com.example.onlineBankingLogin.dto.LoginResponseDTO;
import com.example.onlineBankingLogin.entity.Users;
import com.example.onlineBankingLogin.repository.UserRepository;
import com.example.onlineBankingLogin.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Dummy {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(path = "/hello")
    public String hello() {
        return "hello feefal";
    }

    @PostMapping(path = "/authenticate")
    public LoginResponseDTO createAuthenticationToken(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Email or Password", e);
        }

//        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getEmail());

        Users user = userRepository.getUserByEmail(loginRequestDTO.getEmail());

        String jwt = jwtUtil.generateToken(loginRequestDTO.getEmail());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setJwt(jwt);

        return loginResponseDTO;

    }

}
