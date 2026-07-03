package com.turismo.api.modules.hero.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.hero.entity.HeroSeccion;
import com.turismo.api.modules.hero.service.HeroSeccionService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/hero")
@RequiredArgsConstructor
public class HeroSeccionLegacyController {

	private final HeroSeccionService heroSeccionService;

	@GetMapping("/principal")
	public ResponseEntity<ApiResponse<HeroSeccion>> obtenerPrincipal() {
		return ResponseEntity.ok(ApiResponse.ok("Hero principal encontrado", heroSeccionService.obtenerPrincipal()));
	}
}
