package com.dasa.pedidomedico.resources;

import com.dasa.pedidomedico.business.PedidoBusiness;
import com.dasa.pedidomedico.domain.Pedido;
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
@RequestMapping(value="/pedidos")
@Api(value = "pedido", description = "Recursos do Pedido de Exames")
public class PedidoResource {
	
	@Autowired
	private PedidoBusiness business;

	@GetMapping(value="/{id}")
	@ApiOperation(value = "Busca pedidos por ID")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		Pedido obj = business.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/paciente/{idPaciente}")
	@ApiOperation(value = "Lista todos os pedidos de um paciente")
	public ResponseEntity<List<Pedido>> findByPacienteId(@PathVariable Long idPaciente) {
		List<Pedido> pedidos = business.findByPacienteId(idPaciente);
		return ResponseEntity.ok().body(pedidos);
	}

	@GetMapping(value="/medico/{idMedico}")
	@ApiOperation(value = "Lista todos os pedidos de um medico")
	public ResponseEntity<List<Pedido>> findByMedicoId(@PathVariable Long idMedico) {
		List<Pedido> pedidos = business.findByMedicoId(idMedico);
		return ResponseEntity.ok().body(pedidos);
	}

	@PostMapping
	@ApiOperation(value = "Inclui um novo pedido")
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
		pedido = business.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Exclui um pedido por ID")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		business.delete(id);
		return ResponseEntity.noContent().build();
	}
}
