package com.example.onlineBankingLogin.services;

import com.example.onlineBankingLogin.dto.RegisterRequestDTO;
import com.example.onlineBankingLogin.dto.RegisterResponseDTO;

public interface RegisterService {
    RegisterResponseDTO registerUser(RegisterRequestDTO userRequestDto);
}
