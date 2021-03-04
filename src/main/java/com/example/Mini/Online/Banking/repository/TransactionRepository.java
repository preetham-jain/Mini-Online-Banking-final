package com.example.Mini.Online.Banking.repository;

import com.example.Mini.Online.Banking.entity.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transactions, Long> {

    @Query(value="SELECT * FROM transactions t where t.user_id = ?1 ORDER BY date ASC", nativeQuery = true)
    List<Transactions> getPastTransaction(Long id);

}

