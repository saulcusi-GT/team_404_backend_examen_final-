package com.turismo.api.modules.departamento.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.departamento.entity.DepartamentoInfo;
import com.turismo.api.modules.departamento.service.DepartamentoInfoService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/departamento")
@RequiredArgsConstructor
public class DepartamentoInfoLegacyController {

	private final DepartamentoInfoService departamentoInfoService;

	@GetMapping("/principal")
	public ResponseEntity<ApiResponse<DepartamentoInfo>> obtenerPrincipal() {
		return ResponseEntity.ok(ApiResponse.ok("Departamento principal encontrado", departamentoInfoService.obtenerPrincipal()));
	}
}
