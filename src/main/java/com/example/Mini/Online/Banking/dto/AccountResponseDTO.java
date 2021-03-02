package com.example.Mini.Online.Banking.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class AccountResponseDTO {
    private Long id;
    private String accountNo;
    private double accountBalance;
}
