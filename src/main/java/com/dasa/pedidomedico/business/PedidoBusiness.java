package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.Exame;
import com.dasa.pedidomedico.domain.Medico;
import com.dasa.pedidomedico.domain.Paciente;
import com.dasa.pedidomedico.domain.Pedido;
import com.dasa.pedidomedico.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoBusiness extends GenericBusiness<Pedido, Long>{
	
	@Autowired
	private PedidoRepository repo;

	@Override
	protected PedidoRepository getRepository() {
		return repo;
	}

	@Override
	protected void updateData(Pedido currentObj, Pedido newObj) {
		currentObj.setDtValidade(newObj.getDtValidade());
		currentObj.setObservacao(newObj.getObservacao());
		if (newObj.getPaciente() != null && newObj.getPaciente().getId() != null) {
			currentObj.setPaciente(newObj.getPaciente());
		}
		if (newObj.getMedico() != null && newObj.getMedico().getId() != null) {
			currentObj.setMedico(newObj.getMedico());
		}
	}

	public List<Pedido> findByPacienteId(Long idPaciente) {
		Paciente paciente = new Paciente();
		paciente.setId(idPaciente);
		return repo.findByPaciente(paciente);
	}

	public List<Pedido> findByMedicoId(Long idMedico) {
		Medico medico = new Medico();
		medico.setId(idMedico);
		return repo.findByMedico(medico);
	}

	public Pedido updateExames(Long idPedido, List<Exame> exames) {
		Pedido currentObj = findById(idPedido);
		currentObj.setExames(exames);
		return getRepository().save(currentObj);
	}

}
