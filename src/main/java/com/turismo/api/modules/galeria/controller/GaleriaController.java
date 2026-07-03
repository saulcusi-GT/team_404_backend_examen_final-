package com.turismo.api.modules.galeria.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.galeria.entity.Galeria;
import com.turismo.api.modules.galeria.service.GaleriaService;
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

// Modulo Galeria: exponer endpoints de galeria solo desde este controlador.
@RestController
@RequestMapping("/api/galeria")
@RequiredArgsConstructor
public class GaleriaController {

	private final GaleriaService galeriaService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Galeria>>> listar() {
		return ResponseEntity.ok(ApiResponse.ok("Galeria encontrada", galeriaService.listar()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Galeria>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.ok("Imagen de galeria encontrada", galeriaService.buscarPorId(id)));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Galeria>> crear(@Valid @RequestBody Galeria galeria) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.ok("Imagen de galeria creada", galeriaService.crear(galeria)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Galeria>> actualizar(@PathVariable Long id, @Valid @RequestBody Galeria galeria) {
		return ResponseEntity.ok(ApiResponse.ok("Imagen de galeria actualizada", galeriaService.actualizar(id, galeria)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
		galeriaService.eliminar(id);
		return ResponseEntity.ok(ApiResponse.ok("Imagen de galeria eliminada", null));
	}
}
