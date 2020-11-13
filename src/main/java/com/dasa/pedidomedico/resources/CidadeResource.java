package com.dasa.pedidomedico.resources;

import com.dasa.pedidomedico.business.CidadeBusiness;
import com.dasa.pedidomedico.domain.Cidade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/cidades")
@Api(value = "cidade", description = "Recursos disponíveis para o domínio Cidade")
public class CidadeResource {
	
	@Autowired
	private CidadeBusiness business;

	@GetMapping
	@ApiOperation(value = "Lista todos as cidades")
	public ResponseEntity<List<Cidade>> findAll() {
		List<Cidade> projectStatusList = business.findAll();
		return ResponseEntity.ok().body(projectStatusList);
	}

	@GetMapping(value="/{id}")
	@ApiOperation(value = "Busca cidades por ID")
	public ResponseEntity<Cidade> findById(@PathVariable Long id) {
		Cidade obj = business.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/page")
	@ApiOperation(value = "Lista todos as cidades paginadas")
	public ResponseEntity<Page<Cidade>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cidade> list = business.findPage(page, size, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	@ApiOperation(value = "Inclui uma nova cidade")
	public ResponseEntity<Void> insert(@Valid @RequestBody Cidade cidade) {
		cidade = business.insert(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cidade.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value="/{id}")
	@ApiOperation(value = "Edita uma cidade")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody Cidade cidade) {
		business.update(id, cidade);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Exclui uma cidade por ID")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
}
