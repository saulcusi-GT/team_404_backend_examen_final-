package com.turismo.api.modules.gastronomia.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.gastronomia.entity.Gastronomia;
import com.turismo.api.modules.gastronomia.service.GastronomiaService;
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

// Modulo Gastronomia: exponer endpoints de gastronomia solo desde este controlador.
@RestController
@RequestMapping("/api/gastronomia")
@RequiredArgsConstructor
public class GastronomiaController {

	private final GastronomiaService gastronomiaService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Gastronomia>>> listar() {
		return ResponseEntity.ok(ApiResponse.ok("Gastronomia encontrada", gastronomiaService.listar()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Gastronomia>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.ok("Gastronomia encontrada", gastronomiaService.buscarPorId(id)));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Gastronomia>> crear(@Valid @RequestBody Gastronomia gastronomia) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.ok("Gastronomia creada", gastronomiaService.crear(gastronomia)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Gastronomia>> actualizar(
			@PathVariable Long id,
			@Valid @RequestBody Gastronomia gastronomia) {
		return ResponseEntity.ok(ApiResponse.ok("Gastronomia actualizada", gastronomiaService.actualizar(id, gastronomia)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
		gastronomiaService.eliminar(id);
		return ResponseEntity.ok(ApiResponse.ok("Gastronomia eliminada", null));
	}
}
