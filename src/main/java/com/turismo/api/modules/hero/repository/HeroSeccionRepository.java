package com.turismo.api.modules.hero.repository;

import com.turismo.api.modules.hero.entity.HeroSeccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeroSeccionRepository extends JpaRepository<HeroSeccion, Long> {

	Optional<HeroSeccion> findFirstByOrderByIdAsc();
}
