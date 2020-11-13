package com.dasa.pedidomedico.resources;

import com.dasa.pedidomedico.domain.Paciente;
import com.dasa.pedidomedico.business.PacienteBusiness;
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
@RequestMapping(value="/pacientes")
@Api(value = "paciente", description = "Recursos disponíveis para o domínio Paciente")
public class PacienteResource {
	
	@Autowired
	private PacienteBusiness business;

	@GetMapping
	@ApiOperation(value = "Lista todos os pacientes")
	public ResponseEntity<List<Paciente>> findAll() {
		List<Paciente> projectStatusList = business.findAll();
		return ResponseEntity.ok().body(projectStatusList);
	}

	@GetMapping(value="/{id}")
	@ApiOperation(value = "Busca pacientes por ID")
	public ResponseEntity<Paciente> findById(@PathVariable Long id) {
		Paciente obj = business.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/page")
	@ApiOperation(value = "Lista todos os pacientes paginados")
	public ResponseEntity<Page<Paciente>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Paciente> list = business.findPage(page, size, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	@ApiOperation(value = "Inclui um novo paciente")
	public ResponseEntity<Void> insert(@Valid @RequestBody Paciente paciente) {
		paciente = business.insert(paciente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value="/{id}")
	@ApiOperation(value = "Edita um paciente")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
		business.update(id, paciente);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Exclui um paciente por ID")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
}
