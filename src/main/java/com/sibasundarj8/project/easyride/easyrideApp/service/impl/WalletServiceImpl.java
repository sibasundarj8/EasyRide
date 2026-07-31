package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Ride;
import com.sibasundarj8.project.easyride.easyrideApp.entity.User;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Wallet;
import com.sibasundarj8.project.easyride.easyrideApp.entity.WalletTransaction;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.TransactionMethod;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.TransactionType;
import com.sibasundarj8.project.easyride.easyrideApp.exception.ResourceNotFoundException;
import com.sibasundarj8.project.easyride.easyrideApp.repository.WalletRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IWalletService;
import com.sibasundarj8.project.easyride.easyrideApp.service.IWalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WalletServiceImpl implements IWalletService {

    private final WalletRepository walletRepository;
    private final IWalletTransactionService walletTransactionService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, TransactionMethod transactionMethod, Ride ride, String transactionId) {
        Wallet wallet = findWalletByUser(user);

        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .amount(amount)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .ride(ride)
                .transactionId(transactionId)
                .wallet(wallet)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, TransactionMethod transactionMethod, Ride ride, String transactionId) {
        Wallet wallet = findWalletByUser(user);

        wallet.setBalance(wallet.getBalance() - amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .amount(amount)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .ride(ride)
                .transactionId(transactionId)
                .wallet(wallet)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMoneyFromWallet(Wallet wallet) {
    }

    @Override
    @Transactional(readOnly = true)
    public Wallet  findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));
    }

    @Override
    @Transactional(readOnly = true)
    public Wallet findWalletByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for user with id: " + user.getId()));
    }
}