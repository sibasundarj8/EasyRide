package com.sibasundarj8.project.easyride.easyrideApp.repository;

import com.sibasundarj8.project.easyride.easyrideApp.entity.User;
import com.sibasundarj8.project.easyride.easyrideApp.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}