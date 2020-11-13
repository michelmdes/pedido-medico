package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.Medico;
import com.dasa.pedidomedico.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoBusiness extends GenericBusiness<Medico, Long>{
	
	@Autowired
	private MedicoRepository repo;

	@Override
	protected MedicoRepository getRepository() {
		return repo;
	}

	@Override
	protected void updateData(Medico currentObj, Medico newObj) {
		currentObj.setNome(newObj.getNome());
		currentObj.setSexo(newObj.getSexo());
		currentObj.setNumeroConselho(newObj.getNumeroConselho());
		currentObj.setTipoConselho(newObj.getTipoConselho());
		currentObj.setEstadoConselho(newObj.getEstadoConselho());
		if (newObj.getEndereco() != null) {
			currentObj.setEndereco(newObj.getEndereco());
		}
	}

	public List<Medico> findByConselho(String numeroConselho, String tipoConselho, String estadoConselho) {
		return repo.findByNumeroConselhoAndTipoConselhoAndEstadoConselho(numeroConselho, tipoConselho, estadoConselho);
	}

}
