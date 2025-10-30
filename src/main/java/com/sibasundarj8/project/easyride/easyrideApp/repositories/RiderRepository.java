package com.sibasundarj8.project.easyride.easyrideApp.repositories;

import com.sibasundarj8.project.easyride.easyrideApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
}
