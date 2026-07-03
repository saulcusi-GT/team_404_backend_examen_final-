package com.turismo.api.modules.hero.service;

import com.turismo.api.exception.ResourceNotFoundException;
import com.turismo.api.modules.hero.entity.HeroSeccion;
import com.turismo.api.modules.hero.repository.HeroSeccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HeroSeccionService {

	private final HeroSeccionRepository heroSeccionRepository;

	@Transactional(readOnly = true)
	public List<HeroSeccion> listar() {
		return heroSeccionRepository.findAll();
	}

	@Transactional(readOnly = true)
	public HeroSeccion buscarPorId(Long id) {
		return heroSeccionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hero no encontrado con id: " + id));
	}

	@Transactional(readOnly = true)
	public HeroSeccion obtenerPrincipal() {
		return heroSeccionRepository.findByOrderByIdAsc(PageRequest.of(0, 1)).stream()
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Aun no hay una seccion hero cargada"));
	}

	public HeroSeccion crear(HeroSeccion hero) {
		hero.setId(null);
		return heroSeccionRepository.save(hero);
	}

	public HeroSeccion actualizar(Long id, HeroSeccion datos) {
		HeroSeccion hero = buscarPorId(id);
		hero.setTextoSuperior(datos.getTextoSuperior());
		hero.setTitulo(datos.getTitulo());
		hero.setImagenUrl(datos.getImagenUrl());
		return heroSeccionRepository.save(hero);
	}

	public void eliminar(Long id) {
		HeroSeccion hero = buscarPorId(id);
		heroSeccionRepository.delete(hero);
	}
}
