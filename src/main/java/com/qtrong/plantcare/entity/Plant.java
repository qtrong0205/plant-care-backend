package com.qtrong.plantcare.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Plant {
    @Id
    @Column(name = "plant_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String plantId;
    private String name;
    @Enumerated(EnumType.STRING)
    private PlantType species;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "planted_at")
    private Date plantedAt;
    @Column(name = "watering_interval_days")
    private int wateringIntervalDays;
    @Column(name = "last_watered_at")
    private Date lastWateredAt;
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(
        name = "user_id"
    )
    @JsonBackReference
    private User user;
}
