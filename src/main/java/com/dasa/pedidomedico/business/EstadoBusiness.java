package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.Estado;
import com.dasa.pedidomedico.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoBusiness extends GenericBusiness<Estado, Long>{
	
	@Autowired
	private EstadoRepository repo;

	@Override
	protected EstadoRepository getRepository() {
		return repo;
	}

	@Override
	protected void updateData(Estado currentObj, Estado newObj) {
		currentObj.setNome(newObj.getNome());
	}

}
