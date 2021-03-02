package com.example.Mini.Online.Banking.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Transactions {
    @Id
    @GenericGenerator(name = "transaction_id_seq", strategy = "increment")
    @GeneratedValue(generator = "transactions_id_seq", strategy = GenerationType.AUTO)
    private int id;

    private String type;
    private String status;
    private double amount;
    private double availableBalance;

}
