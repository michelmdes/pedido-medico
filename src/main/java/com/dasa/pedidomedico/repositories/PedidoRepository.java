package com.dasa.pedidomedico.repositories;

import com.dasa.pedidomedico.domain.Medico;
import com.dasa.pedidomedico.domain.Paciente;
import com.dasa.pedidomedico.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByPaciente(Paciente paciente);

    List<Pedido> findByMedico(Medico medico);

}
