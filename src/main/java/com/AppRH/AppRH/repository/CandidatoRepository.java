package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Vaga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidatoRepository extends CrudRepository<Candidato, String> {
    Iterable<Candidato> findByVaga(Vaga vaga);

    Candidato findByRg(String rg);

    Candidato findById(long id);

    List<Candidato> findByNameCandidato(String name);

}
