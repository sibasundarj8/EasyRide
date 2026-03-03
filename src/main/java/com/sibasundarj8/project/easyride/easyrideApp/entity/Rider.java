package com.sibasundarj8.project.easyride.easyrideApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rider {

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private Double rating;
}