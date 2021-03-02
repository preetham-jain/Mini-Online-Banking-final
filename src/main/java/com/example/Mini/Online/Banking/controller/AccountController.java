package com.example.Mini.Online.Banking.controller;

import com.example.Mini.Online.Banking.dto.AccountResponseDTO;
import com.example.Mini.Online.Banking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public List<AccountResponseDTO> getAccountsById(@PathVariable("id") Long id) {
        return accountService.getAccountsById(id);
    }


}
