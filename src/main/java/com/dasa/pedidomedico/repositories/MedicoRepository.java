package com.dasa.pedidomedico.repositories;

import com.dasa.pedidomedico.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findByNumeroConselhoAndTipoConselhoAndEstadoConselho(String numeroConselho, String tipoConselho, String estadoConselho);

}
