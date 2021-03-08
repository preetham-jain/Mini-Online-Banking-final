package com.example.Mini.Online.Banking.services.impl;

import com.example.Mini.Online.Banking.dto.*;
import com.example.Mini.Online.Banking.entity.Accounts;
import com.example.Mini.Online.Banking.entity.Transactions;
import com.example.Mini.Online.Banking.repository.AccountRepository;
import com.example.Mini.Online.Banking.repository.TransactionRepository;
import com.example.Mini.Online.Banking.services.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TransactionServiceimpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public WithdrawResponseDTO depositToAccountById(WithdrawRequestDTO withdrawRequestDTO, Long user_id) {


        WithdrawResponseDTO withdrawResponseDTO = new WithdrawResponseDTO();
        String accountNo = withdrawRequestDTO.getAccountNo();
        List<Accounts> accountsList=accountRepository.getAccountFromId(user_id);
        boolean accountExist=false;
        for(Accounts accounts:accountsList)
        {
            String savedaccountNo = accounts.getAccountNo();
            if(accountNo.equalsIgnoreCase(savedaccountNo))
            {
                accountExist=true;
            }
        }

//        System.out.println(accountsList);

        if(accountExist==false) {
            withdrawResponseDTO.setMessage("Account Number does not Exist");
            withdrawResponseDTO.setCurrentBalance(0);
            withdrawResponseDTO.setAmount(0);
            return withdrawResponseDTO;
        }



        String givenCode = withdrawRequestDTO.getPin();

        double depositAmount = withdrawRequestDTO.getAmount();

        Accounts UserAccount = accountRepository.getAccountForWithdrawal(user_id,accountNo);

        //user doesn't exist goes here

        String actualPin = UserAccount.getPin();

        double total = 0;

        double currBalance = UserAccount.getAccountBalance();

        if(depositAmount < 0) {
            withdrawResponseDTO.setCurrentBalance(currBalance);
            withdrawResponseDTO.setMessage("Invalid input for deposit amount!");
            withdrawResponseDTO.setAmount(withdrawRequestDTO.getAmount());
            return withdrawResponseDTO;
        }

        if(actualPin.equalsIgnoreCase(givenCode)) {
            total = currBalance + depositAmount;

            UserAccount.setAccountBalance(total);

            accountRepository.save(UserAccount);

            Transactions transactions = new Transactions();
            transactions.setAmount(depositAmount);
            String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(Calendar.getInstance().getTime());
            transactions.setDate(timeStamp);
            transactions.setUser_id(user_id);
            transactions.setRecipientAccNo(null);
            transactions.setMyAccNo(withdrawRequestDTO.getAccountNo());
            transactions.setStatus("Success");
            transactions.setType("Deposit");

            transactionRepository.save(transactions);

        }
        else{
            withdrawResponseDTO.setCurrentBalance(currBalance);
            withdrawResponseDTO.setMessage("Invalid Pin");
            withdrawResponseDTO.setAmount(0);
            return withdrawResponseDTO;
        }
        withdrawResponseDTO.setCurrentBalance(total);
        withdrawResponseDTO.setMessage("Success");
        withdrawResponseDTO.setAmount(withdrawRequestDTO.getAmount());
        return withdrawResponseDTO;
    }

    @Override
    @Transactional
    public String transferToAccountByAccountNo(TransactionRequestDTO transactionRequestDTO, Long user_id) {
        String senderAccountNo = transactionRequestDTO.getSenderAccount();

        String recipientAccountNo = transactionRequestDTO.getRecipientAccount();

        Accounts senderAccount = accountRepository.getAccountByAccountNo(senderAccountNo);

        Accounts recipientAccount = accountRepository.getAccountByAccountNo(recipientAccountNo);

        if(senderAccount == null) {
            return "Sender account does not exist";
        }
        if(recipientAccount == null) {
            return "Recipient account does not exist";
        }

        double transferAmount = transactionRequestDTO.getAmount();

        if(transferAmount < 0) {
            return "Invalid input for transfer amount";
        }

        String givenPin = transactionRequestDTO.getPin();

        String actualPin = senderAccount.getPin();

        if(givenPin.equalsIgnoreCase(actualPin)) {
            double senderBalanceAfterTransfer = 0;
            double recipientBalanceAfterTransfer = 0;
            double senderBalance = senderAccount.getAccountBalance();
            double recipientBalance = recipientAccount.getAccountBalance();

            if(senderBalance > transferAmount) {
                senderBalanceAfterTransfer = senderBalance - transferAmount;
                recipientBalanceAfterTransfer = recipientBalance + transferAmount;

                senderAccount.setAccountBalance(senderBalanceAfterTransfer);
                recipientAccount.setAccountBalance(recipientBalanceAfterTransfer);

                Transactions transactions = new Transactions();
                transactions.setAmount(transferAmount);
                String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(Calendar.getInstance().getTime());
                transactions.setDate(timeStamp);
                transactions.setUser_id(user_id);
                transactions.setRecipientAccNo(recipientAccountNo);
                transactions.setMyAccNo(senderAccountNo);
                transactions.setStatus("Success");
                transactions.setType("Transfer");

                transactionRepository.save(transactions);
            }
            else {
                return "Insufficient Balance";
            }
        }
        else {
            return "Invalid pin";
        }
        return "Success";

    }


    @Override
    @Transactional
    public List<TransactionResponseDTO> getPastTransactions(Long user_id)
    {

        List<Transactions> transactionsList=transactionRepository.getPastTransaction(user_id);

          List<TransactionResponseDTO> transactionResponseDTOS=new ArrayList<>();
          for(Transactions transactions:transactionsList)
          {
              TransactionResponseDTO transactionResponseDTO=new TransactionResponseDTO();
              BeanUtils.copyProperties(transactions,transactionResponseDTO);
              transactionResponseDTOS.add(transactionResponseDTO);
          }
          return transactionResponseDTOS;
    }

    @Override
    @Transactional
    public List<TransactionResponseDTO> searchTransactionType(Long user_id,String transactionType)
    {
        List<Transactions> transactionList=transactionRepository.getTransactionType(user_id,transactionType);
        List<TransactionResponseDTO> transactionResponseDTOS=new ArrayList<>();

        for(Transactions transactions:transactionList)
        {
            TransactionResponseDTO transactionResponseDTO=new TransactionResponseDTO();
            BeanUtils.copyProperties(transactions,transactionResponseDTO);
            transactionResponseDTOS.add(transactionResponseDTO);
        }

        return transactionResponseDTOS;

    }
}

