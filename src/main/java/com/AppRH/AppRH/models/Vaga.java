package com.AppRH.AppRH.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Vaga implements Serializable {

    private static final long serialVersonUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String date;

    @NotEmpty
    private String salario;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private List<Candidato> candidatos;
}
