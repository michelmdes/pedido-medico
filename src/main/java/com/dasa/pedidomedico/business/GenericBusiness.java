package com.dasa.pedidomedico.business;

import com.dasa.pedidomedico.business.exceptions.ObjectNotFoundException;
import com.dasa.pedidomedico.domain.GenericDomain;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Classe generica da business, onde encapsula os metodos crud padrao
 * @param <T> Classe de dominio
 * @param <ID> Tipo do ID
 * @author Michel Mendes
 * @since 12/11/2020
 */
public abstract class GenericBusiness<T extends GenericDomain, ID> {

	protected abstract JpaRepository getRepository();

	public List<T> findAll() {
		return getRepository().findAll();
	}
	
	public T findById(ID id) {
		Optional<T> obj = getRepository().findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
	}

	public Page<T> findPage(Integer page, Integer size, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
		return getRepository().findAll(pageRequest);
	}

	public T insert(T obj) {
		return (T) getRepository().save(obj);
	}

	public T update(ID id, T obj) {
		T currentObj = findById(id);
		updateData(currentObj, obj);
		return (T) getRepository().save(currentObj);
	}

	public void delete(ID id) {
		findById(id);
		try {
			getRepository().deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir esse objeto pois tem filhos associados.");
		}
	}

	/**
	 * Antes de fazer um update em uma tabela, é necessário fazer um findById para obter o objeto com todos os seus atributos,
	 * e então alterar apenas os campos que deseja ser alterado, evitando que apague atributos que não chegaram por parâmetro.
	 * Esse método faz exatamente isso, diz quais são os campos que podem ser alterados (Não permite alterar rg e cpf)
	 * @param currentObj obj como está na base de dados
	 * @param newObj obj que será alterado
	 */
	protected abstract void updateData(T currentObj, T newObj);

}
