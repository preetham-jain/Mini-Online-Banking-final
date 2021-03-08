package com.example.Mini.Online.Banking.controller;


import com.example.Mini.Online.Banking.dto.TransactionRequestDTO;
import com.example.Mini.Online.Banking.dto.TransactionResponseDTO;
import com.example.Mini.Online.Banking.dto.WithdrawRequestDTO;
import com.example.Mini.Online.Banking.dto.WithdrawResponseDTO;
import com.example.Mini.Online.Banking.dto.SearchRequestDTO;
import com.example.Mini.Online.Banking.services.TransactionService;
import com.example.Mini.Online.Banking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/deposit")
    public WithdrawResponseDTO depositToAccount(@RequestHeader(value = "Authorization") String jwt, @RequestBody WithdrawRequestDTO withdrawRequestDTO) {
        System.out.println("before jwt");
        Long id = Long.parseLong(jwtUtil.extractId(jwt.substring(7)));
        return transactionService.depositToAccountById(withdrawRequestDTO, id);
    }

    @PutMapping("/transfer")
    public String transferAmount(@RequestHeader(value = "Authorization") String jwt, @RequestBody TransactionRequestDTO transactionRequestDTO) {
        Long user_id = Long.parseLong(jwtUtil.extractId(jwt.substring(7)));
        return transactionService.transferToAccountByAccountNo(transactionRequestDTO, user_id);
    }

    @GetMapping("/pastTransaction")
    public List<TransactionResponseDTO> getPastTransactions(@RequestHeader(value = "Authorization") String jwt) {
        Long id = Long.parseLong(jwtUtil.extractId(jwt.substring(7)));
        return transactionService.getPastTransactions(id);
    }

    @PostMapping("/searchTransactionType")
    public List<TransactionResponseDTO> searchTransactionType(@RequestHeader(value = "Authorization") String jwt,@RequestBody SearchRequestDTO searchRequestDTO) {

        Long id = Long.parseLong(jwtUtil.extractId(jwt.substring(7)));

        return transactionService.searchTransactionType(id,searchRequestDTO.getTransactionType());
    }




}
