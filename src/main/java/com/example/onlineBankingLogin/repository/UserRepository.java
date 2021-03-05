package com.example.onlineBankingLogin.repository;

import com.example.onlineBankingLogin.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {

    Users findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Users getUserByEmail(String email);
}
