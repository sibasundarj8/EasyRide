package com.sibasundarj8.project.easyride.easyrideApp.service;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entity.User;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Wallet;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.TransactionMethod;

public interface IWalletService {

    Wallet createNewWallet(User user);

    Wallet addMoneyToWallet(User user, Double amount, TransactionMethod transactionMethod, Ride ride, String transactionId);

    Wallet deductMoneyFromWallet(User user, Double amount, TransactionMethod transactionMethod, Ride ride, String transactionId);

    void withdrawAllMoneyFromWallet(Wallet wallet);

    Wallet findWalletById(Long walletId);

    Wallet findWalletByUser(User user);
}