package com.turismo.api.modules.galeria.controller;

import com.turismo.api.common.ApiResponse;
import com.turismo.api.modules.galeria.entity.Galeria;
import com.turismo.api.modules.galeria.service.GaleriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Módulo Galería: exponer endpoints de galería solo desde este controlador.
@RestController
@RequestMapping("/api/galeria")
@RequiredArgsConstructor
public class GaleriaController {

    private final GaleriaService galeriaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Galeria>>> listar() {
        return ResponseEntity.ok(
                ApiResponse.ok("Galería encontrada", galeriaService.listar())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Galeria>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.ok("Imagen de galería encontrada", galeriaService.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Galeria>> crear(@Valid @RequestBody Galeria galeria) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.ok("Imagen de galería creada", galeriaService.crear(galeria))
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Galeria>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Galeria galeria) {

        return ResponseEntity.ok(
                ApiResponse.ok("Imagen de galería actualizada",
                        galeriaService.actualizar(id, galeria))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {

        galeriaService.eliminar(id);

        return ResponseEntity.ok(
                ApiResponse.ok("Imagen de galería eliminada", null)
        );
    }
}