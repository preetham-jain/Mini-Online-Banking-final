package com.example.Mini.Online.Banking.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
//import javax.websocket.server.ServerEndpoint;

@Entity
@Getter
@Setter
public class Transactions {
    @Id
    @GenericGenerator(name = "transaction_id_seq", strategy = "increment")
    @GeneratedValue(generator = "transactions_id_seq", strategy = GenerationType.AUTO)
    private Long id;

    private String recipientAccNo;
    private String Date;
    private String type;
    private String status;
    private double amount;
    private Long user_id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users user;

}
