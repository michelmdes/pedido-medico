package com.dasa.pedidomedico.resources;

import com.dasa.pedidomedico.business.ExameBusiness;
import com.dasa.pedidomedico.domain.Exame;
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
@RequestMapping(value="/exames")
@Api(value = "exame", description = "Recursos disponíveis para o domínio Exame")
public class ExameResource {
	
	@Autowired
	private ExameBusiness business;

	@GetMapping
	@ApiOperation(value = "Lista todos os exames")
	public ResponseEntity<List<Exame>> findAll() {
		List<Exame> projectStatusList = business.findAll();
		return ResponseEntity.ok().body(projectStatusList);
	}

	@GetMapping(value="/{id}")
	@ApiOperation(value = "Busca exames por ID")
	public ResponseEntity<Exame> findById(@PathVariable Long id) {
		Exame obj = business.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/page")
	@ApiOperation(value = "Lista todos os exames paginados")
	public ResponseEntity<Page<Exame>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Exame> list = business.findPage(page, size, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	@ApiOperation(value = "Inclui um novo exame")
	public ResponseEntity<Void> insert(@Valid @RequestBody Exame exame) {
		exame = business.insert(exame);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(exame.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value="/{id}")
	@ApiOperation(value = "Edita um exame")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody Exame exame) {
		business.update(id, exame);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Exclui um exame por ID")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
}
