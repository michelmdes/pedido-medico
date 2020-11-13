package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.Cidade;
import com.dasa.pedidomedico.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeBusiness extends GenericBusiness<Cidade, Long>{
	
	@Autowired
	private CidadeRepository repo;

	@Override
	protected CidadeRepository getRepository() {
		return repo;
	}

	@Override
	protected void updateData(Cidade currentObj, Cidade newObj) {
		currentObj.setNome(newObj.getNome());
	}

}
