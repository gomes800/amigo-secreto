package com.gom.amigo_secreto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "chosen_name_id")
    private Names chosenName;

    private Integer footwearNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

}
