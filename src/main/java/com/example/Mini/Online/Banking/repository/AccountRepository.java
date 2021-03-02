package com.example.Mini.Online.Banking.repository;

import com.example.Mini.Online.Banking.entity.Accounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Accounts, Long> {
    @Query(value = "SELECT * FROM accounts a WHERE a.user_id = ?1", nativeQuery = true)
    List<Accounts> getAccountFromId(Long id);
}
