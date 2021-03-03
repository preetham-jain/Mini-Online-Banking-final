package com.example.Mini.Online.Banking.services;

import com.example.Mini.Online.Banking.dto.TransactionRequestDTO;
import com.example.Mini.Online.Banking.dto.TransactionResponseDTO;
import com.example.Mini.Online.Banking.dto.WithdrawRequestDTO;
import com.example.Mini.Online.Banking.dto.WithdrawResponseDTO;

import java.util.List;

public interface TransactionService {
    WithdrawResponseDTO depositToAccountById(WithdrawRequestDTO withdrawRequestDTO, Long user_id);

    String transferToAccountByAccountNo(TransactionRequestDTO transactionRequestDTO, Long user_id);

    List<TransactionResponseDTO> getPastTransactions(Long user_id);
}
