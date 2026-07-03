package com.turismo.api.modules.lugares.repository;

import com.turismo.api.modules.lugares.entity.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;

// Modulo Lugares: agregar consultas propias aqui.
public interface LugarRepository extends JpaRepository<Lugar, Long> {
}
