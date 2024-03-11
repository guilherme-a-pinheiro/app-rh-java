package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.models.Vaga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VagaRepository extends CrudRepository<Vaga, String> {
    Vaga findById(long id);
    List<Vaga> findByName(String name);
}
