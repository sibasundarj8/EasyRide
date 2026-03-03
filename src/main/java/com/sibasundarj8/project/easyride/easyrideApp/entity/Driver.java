package com.sibasundarj8.project.easyride.easyrideApp.entity;

import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private String vehicleNo;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private Double rating;
    private Boolean available;

    @Column(columnDefinition = "geography(Point, 4326)")
    private Point currentLocation;
}