package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.domain.Endereco;
import com.dasa.pedidomedico.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoBusiness extends GenericBusiness<Endereco, Long>{
	
	@Autowired
	private EnderecoRepository repo;

	@Override
	protected EnderecoRepository getRepository() {
		return repo;
	}

	@Override
	protected void updateData(Endereco currentObj, Endereco newObj) {
		currentObj.setLogradouro(newObj.getLogradouro());
		currentObj.setBairro(newObj.getBairro());
		currentObj.setCep(newObj.getCep());
		currentObj.setComplemento(newObj.getComplemento());
		currentObj.setNumero(newObj.getNumero());
		if (newObj.getCidade() != null && newObj.getCidade().getId() != null) {
			currentObj.setCidade(newObj.getCidade());
		}
	}

}
