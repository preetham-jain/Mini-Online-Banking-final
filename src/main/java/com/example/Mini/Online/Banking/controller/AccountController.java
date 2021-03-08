package com.example.Mini.Online.Banking.controller;

import com.example.Mini.Online.Banking.dto.*;
import com.example.Mini.Online.Banking.services.AccountService;
import com.example.Mini.Online.Banking.services.TransactionService;
import com.example.Mini.Online.Banking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getAccounts")
    public List<AccountResponseDTO> getAccountsById(@RequestHeader(value = "Authorization") String jwt) {
        Long id = Long.parseLong(jwtUtil.extractId(jwt.substring(7)));
        return accountService.getAccountsById(id);
    }

    @PutMapping("/withdraw")
    public WithdrawResponseDTO withdrawFromAccount(@RequestHeader(value = "Authorization") String jwt, @RequestBody WithdrawRequestDTO withdrawRequestDTO) {
        Long id = Long.parseLong(jwtUtil.extractId(jwt.substring(7)));
        return accountService.withdrawFromAccountById(withdrawRequestDTO, id);
    }

    @PostMapping("/createAccount")
    public CreateAccountResponseDTO createAccount(@RequestHeader(value = "Authorization") String jwt, @RequestBody CreateAccountRequestDTO createAccountRequestDTO) {
        Long id = Long.parseLong(jwtUtil.extractId(jwt.substring(7)));
        return accountService.createAccount(createAccountRequestDTO, id);
    }

    @DeleteMapping("/deleteAccount")
    public String deleteAccount(@RequestHeader(value= "Authorization") String jwt, @RequestBody DeleteRequestDTO deleteRequestDTO)
    {
        return "hello";
    }


 }
