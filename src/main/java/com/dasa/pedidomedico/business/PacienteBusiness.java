package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.Paciente;
import com.dasa.pedidomedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteBusiness extends GenericBusiness<Paciente, Long>{
	
	@Autowired
	private PacienteRepository repo;

	@Override
	protected PacienteRepository getRepository() {
		return repo;
	}

	@Override
	protected void updateData(Paciente currentObj, Paciente newObj) {
		currentObj.setNome(newObj.getNome());
		currentObj.setDtNascimento(newObj.getDtNascimento());
		currentObj.setSexo(newObj.getSexo());
		currentObj.setNomeMae(newObj.getNomeMae());
		currentObj.setTelefone(newObj.getTelefone());
		currentObj.setEmail(newObj.getEmail());
		if (newObj.getEndereco() != null)
			currentObj.setEndereco(newObj.getEndereco());
	}

}
