package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.CategoriaExame;
import com.dasa.pedidomedico.domain.Paciente;
import com.dasa.pedidomedico.repositories.CategoriaExameRepository;
import com.dasa.pedidomedico.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaExameBusiness extends GenericBusiness<CategoriaExame, Long>{
	
	@Autowired
	private CategoriaExameRepository repo;

	@Override
	protected CategoriaExameRepository getRepository() {
		return repo;
	}

	@Override
	protected void updateData(CategoriaExame currentObj, CategoriaExame newObj) {
		currentObj.setNome(newObj.getNome());
	}

}
