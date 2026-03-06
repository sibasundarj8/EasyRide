package com.sibasundarj8.project.easyride.easyrideApp.repository;

import com.sibasundarj8.project.easyride.easyrideApp.entity.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = """
                SELECT d.*
                FROM driver d
                JOIN app_user u ON d.user_id = u.id
                WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 0.03)
                ORDER BY d.current_location <-> :pickupLocation
                LIMIT 10
            """, nativeQuery = true)
    List<Driver> findTenNearestDrivers(@Param("pickupLocation") Point pickupLocation);

    @Query(value = """
                SELECT d.*
                FROM driver d
                JOIN app_user u ON d.user_id = u.id
                WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 0.05)
                ORDER BY d.rating DESC, d.current_location <-> :pickupLocation
                LIMIT 10
            """, nativeQuery = true)
    List<Driver> findTenNearByTopRatedDrivers(@Param("pickupLocation") Point pickupLocation);
}