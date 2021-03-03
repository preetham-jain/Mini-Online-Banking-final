package com.example.Mini.Online.Banking.controller;


import com.example.Mini.Online.Banking.dto.TransactionRequestDTO;
import com.example.Mini.Online.Banking.dto.TransactionResponseDTO;
import com.example.Mini.Online.Banking.dto.WithdrawRequestDTO;
import com.example.Mini.Online.Banking.dto.WithdrawResponseDTO;
import com.example.Mini.Online.Banking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/deposit/{id}")
    public WithdrawResponseDTO depositToAccount(@RequestBody WithdrawRequestDTO withdrawRequestDTO, @PathVariable("id") Long user_id) {
        return transactionService.depositToAccountById(withdrawRequestDTO, user_id);
    }

    @PutMapping("/transfer/{id}")
    public String transferAmount(@RequestBody TransactionRequestDTO transactionRequestDTO, @PathVariable("id") Long user_id) {
        return transactionService.transferToAccountByAccountNo(transactionRequestDTO, user_id);
    }

    @GetMapping("/pastTransaction/{id}")
    public List<TransactionResponseDTO> getPastTransactions(@PathVariable("id") Long id)
    {
        return transactionService.getPastTransactions(id);
    }
}
