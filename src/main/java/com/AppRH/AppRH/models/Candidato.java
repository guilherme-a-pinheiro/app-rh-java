package com.AppRH.AppRH.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(unique = true)
    private String rg;

    @NotEmpty
    private String nomeCandidato;

    @NotEmpty
    private String email;

    @ManyToOne
    private Vaga vaga;
}
