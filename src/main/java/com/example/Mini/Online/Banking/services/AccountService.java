package com.example.Mini.Online.Banking.services;

import com.example.Mini.Online.Banking.dto.AccountRequestDTO;
import com.example.Mini.Online.Banking.dto.AccountResponseDTO;

import java.util.List;

public interface AccountService {
    List<AccountResponseDTO> getAccountsById(Long id);
}
