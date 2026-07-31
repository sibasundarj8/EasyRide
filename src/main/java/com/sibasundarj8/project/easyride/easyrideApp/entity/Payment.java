package com.sibasundarj8.project.easyride.easyrideApp.entity;

import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.PaymentMethod;
import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus ;

    @CreationTimestamp
    private LocalDateTime paymentTime;
}