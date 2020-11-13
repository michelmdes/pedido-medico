package com.dasa.pedidomedico.resources;

import com.dasa.pedidomedico.business.EstadoBusiness;
import com.dasa.pedidomedico.domain.Estado;
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
@RequestMapping(value="/estados")
@Api(value = "estado", description = "Recursos disponíveis para o domínio Estado")
public class EstadoResource {
	
	@Autowired
	private EstadoBusiness business;

	@GetMapping
	@ApiOperation(value = "Lista todos os estados")
	public ResponseEntity<List<Estado>> findAll() {
		List<Estado> projectStatusList = business.findAll();
		return ResponseEntity.ok().body(projectStatusList);
	}

	@GetMapping(value="/{id}")
	@ApiOperation(value = "Busca estados por ID")
	public ResponseEntity<Estado> findById(@PathVariable Long id) {
		Estado obj = business.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/page")
	@ApiOperation(value = "Lista todos os estados paginados")
	public ResponseEntity<Page<Estado>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Estado> list = business.findPage(page, size, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	@ApiOperation(value = "Inclui um novo estado")
	public ResponseEntity<Void> insert(@Valid @RequestBody Estado estado) {
		estado = business.insert(estado);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value="/{id}")
	@ApiOperation(value = "Edita um estado")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody Estado estado) {
		business.update(id, estado);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Exclui um estado por ID")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
}
