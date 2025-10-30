package com.sibasundarj8.project.easyride.easyrideApp.entities;

import com.sibasundarj8.project.easyride.easyrideApp.entities.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vehicleNo;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private Double rating;
    private Boolean available;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point currentLocation;
}