package com.turismo.api.modules.gastronomia.repository;

import com.turismo.api.modules.gastronomia.entity.Gastronomia;
import org.springframework.data.jpa.repository.JpaRepository;

// Modulo Gastronomia: agregar consultas propias aqui.
public interface GastronomiaRepository extends JpaRepository<Gastronomia, Long> {
}
