package com.turismo.api.modules.contacto.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.contacto.entity.Contacto;
import com.turismo.api.modules.contacto.service.ContactoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Modulo Contacto: exponer endpoints de contacto solo desde este controlador.
@RestController
@RequestMapping("/api/contactos")
@RequiredArgsConstructor
public class ContactoController {

	private final ContactoService contactoService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Contacto>>> listar() {
		return ResponseEntity.ok(ApiResponse.ok("Contactos encontrados", contactoService.listar()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Contacto>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.ok("Contacto encontrado", contactoService.buscarPorId(id)));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Contacto>> crear(@Valid @RequestBody Contacto contacto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.ok("Contacto creado", contactoService.crear(contacto)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Contacto>> actualizar(@PathVariable Long id, @Valid @RequestBody Contacto contacto) {
		return ResponseEntity.ok(ApiResponse.ok("Contacto actualizado", contactoService.actualizar(id, contacto)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
		contactoService.eliminar(id);
		return ResponseEntity.ok(ApiResponse.ok("Contacto eliminado", null));
	}
}
