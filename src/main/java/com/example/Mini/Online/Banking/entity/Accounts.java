package com.example.Mini.Online.Banking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Accounts {
    @Id
    @GenericGenerator(name = "account_id_seq", strategy = "increment")
    @GeneratedValue(generator = "account_id_seq", strategy = GenerationType.AUTO)

    private Long id;

    @Column(unique = true)
    private String accountNo;
    private double accountBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
