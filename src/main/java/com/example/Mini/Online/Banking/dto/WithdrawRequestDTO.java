package com.example.Mini.Online.Banking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawRequestDTO {
    private String pin;
    private String accountNo;
    private double amount;
}
