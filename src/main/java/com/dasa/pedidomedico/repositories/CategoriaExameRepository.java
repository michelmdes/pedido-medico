package com.dasa.pedidomedico.repositories;

import com.dasa.pedidomedico.domain.CategoriaExame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaExameRepository extends JpaRepository<CategoriaExame, Long> {

}
