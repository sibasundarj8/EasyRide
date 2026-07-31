package com.sibasundarj8.project.easyride.easyrideApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class WalletDto {
    private Long Id;
    private UserDto user;
    private Double balance;
    private List<WalletTransactionDto> transactions;
}