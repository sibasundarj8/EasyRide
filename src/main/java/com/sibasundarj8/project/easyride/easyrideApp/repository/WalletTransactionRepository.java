package com.sibasundarj8.project.easyride.easyrideApp.repository;

import com.sibasundarj8.project.easyride.easyrideApp.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
}