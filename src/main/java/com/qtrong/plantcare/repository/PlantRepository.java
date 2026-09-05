package com.qtrong.plantcare.repository;

import com.qtrong.plantcare.entity.Plant;
import com.qtrong.plantcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, String> {
    Optional<Plant> findByPlantIdAndUser(
            String plantId,
            User user
    );

    List<Plant> findAllByUser_UserId(String userId);
}
