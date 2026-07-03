package com.turismo.api.modules.contacto.repository;

import com.turismo.api.modules.contacto.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

// Modulo Contacto: agregar consultas propias aqui.
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
