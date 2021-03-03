package com.example.Mini.Online.Banking.controller;

import com.example.Mini.Online.Banking.dto.*;
import com.example.Mini.Online.Banking.services.AccountService;
import com.example.Mini.Online.Banking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public List<AccountResponseDTO> getAccountsById(@PathVariable("id") Long id) {
        return accountService.getAccountsById(id);
    }

    @PutMapping("/withdraw/{id}")
    public WithdrawResponseDTO withdrawFromAccount(@RequestBody WithdrawRequestDTO withdrawRequestDTO, @PathVariable Long id) {
        return accountService.withdrawFromAccountById(withdrawRequestDTO, id);
    }

    @PostMapping("/createAccount")
    public CreateAccountResponseDTO createAccount(@RequestBody CreateAccountRequestDTO createAccountRequestDTO)
    {

        return accountService.createAccount(createAccountRequestDTO);
    }


 }
