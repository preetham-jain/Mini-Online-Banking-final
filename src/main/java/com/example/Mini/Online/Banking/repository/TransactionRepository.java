package com.example.Mini.Online.Banking.repository;

import com.example.Mini.Online.Banking.entity.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transactions, Long> {

    @Query(value="SELECT * FROM transactions t where t.user_id = ?1 ORDER BY date DESC", nativeQuery = true)
    List<Transactions> getPastTransaction(Long id);

    @Query(value="SELECT * FROM transactions t where t.user_id = ?1 AND t.type = ?2", nativeQuery = true)
    List<Transactions> getTransactionType(Long id,String transactionType);



}

