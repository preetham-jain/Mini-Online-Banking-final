package com.example.Mini.Online.Banking.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountResponseDTO {

    private String message;
    private String accountNo;
    private double accountBalance;

}
