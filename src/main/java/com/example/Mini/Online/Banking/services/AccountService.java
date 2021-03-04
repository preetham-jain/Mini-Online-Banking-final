package com.example.Mini.Online.Banking.services;

import com.example.Mini.Online.Banking.dto.*;

import java.util.List;

public interface AccountService {
    List<AccountResponseDTO> getAccountsById(Long id);

    WithdrawResponseDTO withdrawFromAccountById(WithdrawRequestDTO withdrawRequestDTO, Long id);

    CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO, Long id);
}
