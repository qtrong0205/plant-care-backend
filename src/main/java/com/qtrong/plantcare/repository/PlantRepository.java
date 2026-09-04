package com.qtrong.plantcare.repository;

import com.qtrong.plantcare.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, String> {
}
