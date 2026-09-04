package com.qtrong.plantcare.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String userId;
    @Column(unique = true, nullable = false)
    @Email
    private String email;
    private String password;
    private String name;
    private Date created_at;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Plant> plants;
}