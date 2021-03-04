package com.example.Mini.Online.Banking.repository;

import com.example.Mini.Online.Banking.entity.Accounts;
import com.example.Mini.Online.Banking.entity.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Accounts, Long> {
    @Query(value = "SELECT * FROM accounts a WHERE a.user_id = ?1", nativeQuery = true)
    List<Accounts> getAccountFromId(Long id);

    @Query(value = "SELECT * FROM accounts WHERE user_id = ?1 and account_no = ?2", nativeQuery = true)
    Accounts getAccountForWithdrawal(Long id, String accountNo);

    @Query(value = "SELECT * FROM accounts WHERE account_no = ?1", nativeQuery = true)
    Accounts getAccountByAccountNo(String accountNo);

    /*
    @Query(value ="SELECT account_no FROM accounts WHERE user_id = 1?", nativeQuery = true)
    List<String> getAllaccounts(Long id);
    */






}
