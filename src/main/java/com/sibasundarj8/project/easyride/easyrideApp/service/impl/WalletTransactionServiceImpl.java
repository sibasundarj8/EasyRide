package com.sibasundarj8.project.easyride.easyrideApp.service.impl;

import com.sibasundarj8.project.easyride.easyrideApp.entity.WalletTransaction;
import com.sibasundarj8.project.easyride.easyrideApp.repository.WalletTransactionRepository;
import com.sibasundarj8.project.easyride.easyrideApp.service.IWalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WalletTransactionServiceImpl implements IWalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        if (walletTransaction.getId() != null && walletTransactionRepository.existsById(walletTransaction.getId())) {
            throw new RuntimeException("Wallet Transaction already exists with id: " + walletTransaction.getId());
        }

        walletTransactionRepository.save(walletTransaction);
    }
}