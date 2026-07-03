package com.turismo.api.modules.hero.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.hero.entity.HeroSeccion;
import com.turismo.api.modules.hero.service.HeroSeccionService;
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

@RestController
@RequestMapping({"/api/hero", "/hero"})
@RequiredArgsConstructor
public class HeroSeccionController {

	private final HeroSeccionService heroSeccionService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<HeroSeccion>>> listar() {
		return ResponseEntity.ok(ApiResponse.ok("Heroes encontrados", heroSeccionService.listar()));
	}

	@GetMapping("/principal")
	public ResponseEntity<ApiResponse<HeroSeccion>> obtenerPrincipal() {
		return ResponseEntity.ok(ApiResponse.ok("Hero principal encontrado", heroSeccionService.obtenerPrincipal()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<HeroSeccion>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.ok("Hero encontrado", heroSeccionService.buscarPorId(id)));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<HeroSeccion>> crear(@Valid @RequestBody HeroSeccion hero) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.ok("Hero creado", heroSeccionService.crear(hero)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<HeroSeccion>> actualizar(@PathVariable Long id, @Valid @RequestBody HeroSeccion hero) {
		return ResponseEntity.ok(ApiResponse.ok("Hero actualizado", heroSeccionService.actualizar(id, hero)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
		heroSeccionService.eliminar(id);
		return ResponseEntity.ok(ApiResponse.ok("Hero eliminado", null));
	}
}
