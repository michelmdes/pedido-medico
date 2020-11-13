package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.Exame;
import com.dasa.pedidomedico.repositories.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExameBusiness extends GenericBusiness<Exame, Long>{
	
	@Autowired
	private ExameRepository repo;

	@Override
	protected ExameRepository getRepository() {
		return repo;
	}

	@Override
	protected void updateData(Exame currentObj, Exame newObj) {
		currentObj.setNome(newObj.getNome());
		if (newObj.getCategoriaExame() != null && newObj.getCategoriaExame().getId() != null) {
			currentObj.setCategoriaExame(newObj.getCategoriaExame());
		}
	}

}
