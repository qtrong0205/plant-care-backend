package com.qtrong.plantcare.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qtrong.plantcare.enums.AiPredictionStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Getter
@Setter
public class History {
    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String historyId;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "predicted_label")
    private String predictedLabel;
    private Float confidence;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "top_k_results", columnDefinition = "json")
    private TopKAiResults topKAiResults;
    @Enumerated(EnumType.STRING)
    private AiPredictionStatus status;
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    @JsonBackReference
    private Plant plant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
