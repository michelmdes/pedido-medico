package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.Exame;
import com.dasa.pedidomedico.domain.Medico;
import com.dasa.pedidomedico.domain.Paciente;
import com.dasa.pedidomedico.domain.Pedido;
import com.dasa.pedidomedico.repositories.PedidoRepository;
import com.dasa.pedidomedico.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

	@Override
	public Pedido insert(Pedido obj) {
		obj.setDtValidade(gerarDataValidade());
		return super.insert(obj);
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

	public List<Pedido> findByPacienteIdOrMedicoId(Long idPaciente, Long idMedico) {

		if (idPaciente == null && idMedico == null) {
			throw new IllegalArgumentException("E necessario pelo menos um parametro para buscar os pedidos!");
		}

		Paciente paciente = new Paciente(idPaciente);
		Medico medico = new Medico(idMedico);

		if (idPaciente == null)
			return repo.findByMedico(medico);
		else if (idMedico == null)
			return repo.findByPaciente(paciente);
		else
			return repo.findByPacienteAndMedico(paciente, medico);
	}

	public Pedido updateExames(Long idPedido, List<Exame> exames) {
		Pedido currentObj = findById(idPedido);
		currentObj.setExames(exames);
		return getRepository().save(currentObj);
	}

	/**
	 * A data de validade foi definida como 24h apos o cadastro
	 * @return dataValidade
	 */
	private Date gerarDataValidade() {
		return DateUtils.addDias(new Date(), 1);
	}

}
