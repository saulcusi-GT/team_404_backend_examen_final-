package com.turismo.api.modules.departamento.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.departamento.entity.Atractivo;
import com.turismo.api.modules.departamento.entity.DepartamentoInfo;
import com.turismo.api.modules.departamento.service.DepartamentoInfoService;
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
@RequestMapping("/api/departamento")
@RequiredArgsConstructor
public class DepartamentoInfoController {

	private final DepartamentoInfoService departamentoInfoService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<DepartamentoInfo>>> listar() {
		return ResponseEntity.ok(ApiResponse.ok("Departamentos encontrados", departamentoInfoService.listar()));
	}

	@GetMapping("/principal")
	public ResponseEntity<ApiResponse<DepartamentoInfo>> obtenerPrincipal() {
		return ResponseEntity.ok(ApiResponse.ok("Departamento principal encontrado", departamentoInfoService.obtenerPrincipal()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<DepartamentoInfo>> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(ApiResponse.ok("Departamento encontrado", departamentoInfoService.buscarPorId(id)));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<DepartamentoInfo>> crear(@Valid @RequestBody DepartamentoInfo departamento) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.ok("Departamento creado", departamentoInfoService.crear(departamento)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<DepartamentoInfo>> actualizar(@PathVariable Long id, @Valid @RequestBody DepartamentoInfo departamento) {
		return ResponseEntity.ok(ApiResponse.ok("Departamento actualizado", departamentoInfoService.actualizar(id, departamento)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
		departamentoInfoService.eliminar(id);
		return ResponseEntity.ok(ApiResponse.ok("Departamento eliminado", null));
	}

	@PostMapping("/{id}/atractivos")
	public ResponseEntity<ApiResponse<DepartamentoInfo>> agregarAtractivo(@PathVariable Long id, @Valid @RequestBody Atractivo atractivo) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.ok("Atractivo agregado", departamentoInfoService.agregarAtractivo(id, atractivo)));
	}

	@PutMapping("/{id}/atractivos/{atractivoId}")
	public ResponseEntity<ApiResponse<DepartamentoInfo>> actualizarAtractivo(
			@PathVariable Long id, @PathVariable Long atractivoId, @Valid @RequestBody Atractivo atractivo) {
		return ResponseEntity.ok(
				ApiResponse.ok("Atractivo actualizado", departamentoInfoService.actualizarAtractivo(id, atractivoId, atractivo)));
	}

	@DeleteMapping("/{id}/atractivos/{atractivoId}")
	public ResponseEntity<ApiResponse<DepartamentoInfo>> eliminarAtractivo(
			@PathVariable Long id, @PathVariable Long atractivoId) {
		return ResponseEntity.ok(
				ApiResponse.ok("Atractivo eliminado", departamentoInfoService.eliminarAtractivo(id, atractivoId)));
	}
}
