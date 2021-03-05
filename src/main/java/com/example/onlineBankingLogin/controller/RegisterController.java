package com.example.onlineBankingLogin.controller;


import com.example.onlineBankingLogin.dto.RegisterRequestDTO;
import com.example.onlineBankingLogin.dto.RegisterResponseDTO;
import com.example.onlineBankingLogin.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public RegisterResponseDTO registerUser(@RequestBody RegisterRequestDTO userRequestDto) {
        return registerService.registerUser(userRequestDto);
    }
}
