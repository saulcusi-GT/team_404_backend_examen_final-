package com.turismo.api.modules.lugares.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.lugares.entity.Lugar;
import com.turismo.api.modules.lugares.service.LugarService;
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

// Modulo Lugares: exponer endpoints de lugares solo desde este controlador.
@RestController
@RequestMapping("/api/lugares")
@RequiredArgsConstructor
public class LugarController {

	private final LugarService lugarService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Lugar>>> listar() {
		return ResponseEntity.ok(ApiResponse.ok("Lugares encontrados", lugarService.listar()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Lugar>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.ok("Lugar encontrado", lugarService.buscarPorId(id)));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Lugar>> crear(@Valid @RequestBody Lugar lugar) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.ok("Lugar creado", lugarService.crear(lugar)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Lugar>> actualizar(@PathVariable Long id, @Valid @RequestBody Lugar lugar) {
		return ResponseEntity.ok(ApiResponse.ok("Lugar actualizado", lugarService.actualizar(id, lugar)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
		lugarService.eliminar(id);
		return ResponseEntity.ok(ApiResponse.ok("Lugar eliminado", null));
	}
}
