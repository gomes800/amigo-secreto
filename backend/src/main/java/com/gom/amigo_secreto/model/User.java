package com.gom.amigo_secreto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;

    private String provider;
    private String providerId;

    @Column(unique = true)
    private String username;

    private String avatarUrl;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;

    @ManyToMany(mappedBy = "participants")
    private List<Group> groups;

    private LocalDateTime registerDate;
    private String wishList;
    private String preferences;
}
