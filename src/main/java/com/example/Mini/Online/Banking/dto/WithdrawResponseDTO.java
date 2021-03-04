package com.example.Mini.Online.Banking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawResponseDTO {
    private String message;
    private double currentBalance;
    private double amount;


}
