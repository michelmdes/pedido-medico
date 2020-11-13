package com.dasa.pedidomedico.resources;

import com.dasa.pedidomedico.business.CategoriaExameBusiness;
import com.dasa.pedidomedico.domain.CategoriaExame;
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
@RequestMapping(value="/categoria-exames")
@Api(value = "categoria-exame", description = "Recursos disponíveis para o domínio CategoriaExame")
public class CategoriaExameResource {
	
	@Autowired
	private CategoriaExameBusiness business;

	@GetMapping
	@ApiOperation(value = "Lista todos as categorias de exames")
	public ResponseEntity<List<CategoriaExame>> findAll() {
		List<CategoriaExame> projectStatusList = business.findAll();
		return ResponseEntity.ok().body(projectStatusList);
	}

	@GetMapping(value="/{id}")
	@ApiOperation(value = "Busca categorias de exames por ID")
	public ResponseEntity<CategoriaExame> findById(@PathVariable Long id) {
		CategoriaExame obj = business.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/page")
	@ApiOperation(value = "Lista todos as categorias de exames paginados")
	public ResponseEntity<Page<CategoriaExame>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<CategoriaExame> list = business.findPage(page, size, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	@ApiOperation(value = "Inclui uma nova categoria de exame")
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaExame exame) {
		exame = business.insert(exame);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(exame.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value="/{id}")
	@ApiOperation(value = "Edita uma categoria de exame")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody CategoriaExame exame) {
		business.update(id, exame);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Exclui uma categoria por ID")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
}
