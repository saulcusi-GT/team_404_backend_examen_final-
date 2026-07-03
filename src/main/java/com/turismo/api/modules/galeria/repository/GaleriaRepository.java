package com.turismo.api.modules.galeria.repository;

import com.turismo.api.modules.galeria.entity.Galeria;
import org.springframework.data.jpa.repository.JpaRepository;

// Modulo Galeria: agregar consultas propias aqui.
public interface GaleriaRepository extends JpaRepository<Galeria, Long> {
}
