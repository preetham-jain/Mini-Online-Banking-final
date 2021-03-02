package com.example.Mini.Online.Banking.services.impl;

import com.example.Mini.Online.Banking.dto.AccountRequestDTO;
import com.example.Mini.Online.Banking.dto.AccountResponseDTO;
import com.example.Mini.Online.Banking.entity.Accounts;
import com.example.Mini.Online.Banking.repository.AccountRepository;
import com.example.Mini.Online.Banking.services.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceimpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountResponseDTO> getAccountsById(Long id) {

        List<Accounts> accountsList = accountRepository.getAccountFromId(id);
        List<AccountResponseDTO> accountResponseDTOS=new ArrayList<>();

        for (Accounts accounts:accountsList) {
            AccountResponseDTO responseDTO = new AccountResponseDTO();
            BeanUtils.copyProperties(accounts, responseDTO);
            accountResponseDTOS.add(responseDTO);
        }
        return  accountResponseDTOS;
    }
}
