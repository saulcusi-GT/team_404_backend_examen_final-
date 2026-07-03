package com.turismo.api.modules.galeria.repository;

import com.turismo.api.modules.galeria.entity.Galeria;
import org.springframework.data.jpa.repository.JpaRepository;

// Módulo Galería: agregar consultas propias aquí.
public interface GaleriaRepository extends JpaRepository<Galeria, Long> {
}