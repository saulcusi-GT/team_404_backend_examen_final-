package com.turismo.api.modules.hero.repository;

import com.turismo.api.modules.hero.entity.HeroSeccion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroSeccionRepository extends JpaRepository<HeroSeccion, Long> {

	List<HeroSeccion> findByOrderByIdAsc(Pageable pageable);

	List<HeroSeccion> findAllByOrderByIdAsc();
}
