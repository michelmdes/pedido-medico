package com.dasa.pedidomedico.resources;

import com.dasa.pedidomedico.business.MedicoBusiness;
import com.dasa.pedidomedico.domain.Medico;
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
@RequestMapping(value="/medicos")
@Api(value = "medico", description = "Recursos disponíveis para o domínio Medico")
public class MedicoResource {
	
	@Autowired
	private MedicoBusiness business;

	@GetMapping
	@ApiOperation(value = "Lista todos os medicos")
	public ResponseEntity<List<Medico>> findAll() {
		List<Medico> projectStatusList = business.findAll();
		return ResponseEntity.ok().body(projectStatusList);
	}

	@GetMapping(value="/{id}")
	@ApiOperation(value = "Busca medicos por ID")
	public ResponseEntity<Medico> findById(@PathVariable Long id) {
		Medico obj = business.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/conselho")
	@ApiOperation(value = "Busca medicos pelo Conselho")
	public ResponseEntity<List<Medico>> findByConselho(
			@RequestParam(value="numero") String numeroConselho,
			@RequestParam(value="tipo", defaultValue="CRM") String tipoConselho,
			@RequestParam(value="estado", defaultValue="SP") String estadoConselho) {
		List<Medico> obj = business.findByConselho(numeroConselho, tipoConselho, estadoConselho);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/page")
	@ApiOperation(value = "Lista todos os medicos paginados")
	public ResponseEntity<Page<Medico>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="size", defaultValue="10") Integer size,
			@RequestParam(value="orderBy", defaultValue="id") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Medico> list = business.findPage(page, size, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	@ApiOperation(value = "Inclui um novo medico")
	public ResponseEntity<Void> insert(@Valid @RequestBody Medico medico) {
		medico = business.insert(medico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value="/{id}")
	@ApiOperation(value = "Edita um medico")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody Medico medico) {
		business.update(id, medico);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Exclui um medico por ID")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
}
