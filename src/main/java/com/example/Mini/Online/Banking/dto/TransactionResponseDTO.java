package com.example.Mini.Online.Banking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDTO {



    private String recipientAccNo;
    private String Date;
    private String type;
    private String status;
    private double amount;
    private Long user_id;

}
