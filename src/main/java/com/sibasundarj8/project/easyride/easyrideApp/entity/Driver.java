package com.sibasundarj8.project.easyride.easyrideApp.entity;

import com.sibasundarj8.project.easyride.easyrideApp.entity.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        indexes = {
                @Index(name = "idx_driver_vehicle_no", columnList = "vehicleNo")
        }
)
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

    @Builder.Default
    private Integer ratingSum = 0;

    @Builder.Default
    private Integer ratingCount = 0;

    @Builder.Default
    private Double rating = 0.0;

    @Builder.Default
    private Boolean available = true;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point currentLocation;
}