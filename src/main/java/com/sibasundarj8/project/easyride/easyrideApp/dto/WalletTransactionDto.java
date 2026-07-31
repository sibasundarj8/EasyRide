package com.sibasundarj8.project.easyride.easyrideApp.dto;

import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.TransactionMethod;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WalletTransactionDto {
    private Long Id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto ride;
    private String transactionId;
    private WalletDto wallet;
    private LocalDateTime timestamp;
}
