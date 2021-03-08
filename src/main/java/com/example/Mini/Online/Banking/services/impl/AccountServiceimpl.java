package com.example.Mini.Online.Banking.services.impl;

import com.example.Mini.Online.Banking.dto.*;
import com.example.Mini.Online.Banking.entity.Accounts;
import com.example.Mini.Online.Banking.entity.Transactions;
import com.example.Mini.Online.Banking.repository.AccountRepository;
import com.example.Mini.Online.Banking.repository.TransactionRepository;
import com.example.Mini.Online.Banking.services.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
public class AccountServiceimpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public List<AccountResponseDTO> getAccountsById(Long id) {

        List<Accounts> accountsList = accountRepository.getAccountFromId(id);
        List<AccountResponseDTO> accountResponseDTOS=new ArrayList<>();

        for (Accounts accounts:accountsList) {
            AccountResponseDTO responseDTO = new AccountResponseDTO();
            BeanUtils.copyProperties(accounts, responseDTO);
            accountResponseDTOS.add(responseDTO);
        }
        System.out.println("API HIT");
        return  accountResponseDTOS;
    }

    @Override
    @Transactional
    public WithdrawResponseDTO withdrawFromAccountById(WithdrawRequestDTO withdrawRequestDTO, Long user_id) {

        WithdrawResponseDTO withdrawResponseDTO = new WithdrawResponseDTO();

        String accountNo = withdrawRequestDTO.getAccountNo();

        String givenCode = withdrawRequestDTO.getPin();

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

        System.out.println(accountsList);

        if(accountExist==false) {
            withdrawResponseDTO.setMessage("Account Number does not Exist");
            withdrawResponseDTO.setCurrentBalance(0);
            withdrawResponseDTO.setAmount(0);
            return withdrawResponseDTO;
        }


        double withdrawalAmount = withdrawRequestDTO.getAmount();

        Accounts UserAccount = accountRepository.getAccountForWithdrawal(user_id,accountNo);

        String actualPin = UserAccount.getPin();

        double total = 0;

        double currBalance = UserAccount.getAccountBalance();

        if(withdrawalAmount < 0) {
            withdrawResponseDTO.setCurrentBalance(currBalance);
            withdrawResponseDTO.setMessage("Invalid input for withdrawal amount!");
            withdrawResponseDTO.setAmount(withdrawRequestDTO.getAmount());
            return withdrawResponseDTO;
        }


        if(actualPin.equalsIgnoreCase(givenCode)) {
            if (currBalance > withdrawalAmount) {
                total = currBalance - withdrawalAmount;
            } else {
                withdrawResponseDTO.setCurrentBalance(total);
                withdrawResponseDTO.setMessage("InSufficient Balance");
                withdrawResponseDTO.setAmount(withdrawRequestDTO.getAmount());
                return withdrawResponseDTO;
            }
            UserAccount.setAccountBalance(total);

            accountRepository.save(UserAccount);

            Transactions transactions = new Transactions();
            transactions.setAmount(withdrawalAmount);
            String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(Calendar.getInstance().getTime());
            transactions.setDate(timeStamp);
            transactions.setUser_id(user_id);
            transactions.setRecipientAccNo(null);
            transactions.setMyAccNo(withdrawRequestDTO.getAccountNo());
            transactions.setStatus("Success");
            transactions.setType("Withdraw");

            transactionRepository.save(transactions);

        }
        else{
            withdrawResponseDTO.setCurrentBalance(currBalance);
            withdrawResponseDTO.setMessage("Invalid Pin");
            return withdrawResponseDTO;
        }
        withdrawResponseDTO.setCurrentBalance(total);
        withdrawResponseDTO.setMessage("Success");
        withdrawResponseDTO.setAmount(withdrawRequestDTO.getAmount());
        return withdrawResponseDTO;
    }


    @Override
    @Transactional
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO, Long user_id) {

        CreateAccountResponseDTO createAccountResponseDTO=new CreateAccountResponseDTO();

        Accounts accounts = new Accounts();

        BeanUtils.copyProperties(createAccountRequestDTO,accounts);

        Random rand = new Random();

        // Generate random integers in range 0 to 999
        int No = rand.nextInt(10000);
        String accountNo = "" + No;

        createAccountResponseDTO.setAccountBalance(0);
        createAccountResponseDTO.setAccountNo(accountNo);
        createAccountResponseDTO.setMessage("Account created Successfully");
        accounts.setAccountNo(accountNo);
        accounts.setUser_id(user_id);
        accounts.setPin(createAccountRequestDTO.getPin());
        accountRepository.save(accounts);

        return createAccountResponseDTO;

    }


}
