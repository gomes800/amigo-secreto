package com.gom.amigo_secreto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "names")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Names {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer footwearNumber;
    private Boolean taken = false;

}
