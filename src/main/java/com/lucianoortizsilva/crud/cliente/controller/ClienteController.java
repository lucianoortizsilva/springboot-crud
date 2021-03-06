package com.lucianoortizsilva.crud.cliente.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucianoortizsilva.crud.cliente.dto.ClienteDTO;
import com.lucianoortizsilva.crud.cliente.model.Cliente;
import com.lucianoortizsilva.crud.cliente.service.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/clientes")
public class ClienteController {

	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody final ClienteDTO dto) {
		Cliente cliente = this.clienteService.fromDTO(dto);
		cliente = this.clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Void> update(@Valid @RequestBody final ClienteDTO dto, @PathVariable(value = "id") final Long id) {
		dto.setId(id);
		this.clienteService.update(dto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") final Long id) {
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Cliente> findById(@PathVariable(value = "id") final Long id) {
		final Optional<Cliente> cliente = this.clienteService.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok().body(cliente.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}