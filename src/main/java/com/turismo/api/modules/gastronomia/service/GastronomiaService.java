package com.turismo.api.modules.gastronomia.service;

import com.turismo.api.exception.ResourceNotFoundException;
import com.turismo.api.modules.gastronomia.entity.Gastronomia;
import com.turismo.api.modules.gastronomia.repository.GastronomiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Modulo Gastronomia: agregar reglas de negocio de gastronomia solo en este servicio.
@Service
@RequiredArgsConstructor
public class GastronomiaService {

	private final GastronomiaRepository gastronomiaRepository;

	public List<Gastronomia> listar() {
		return gastronomiaRepository.findAll();
	}

	public Gastronomia buscarPorId(Long id) {
		return gastronomiaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gastronomia no encontrada con id: " + id));
	}

	public Gastronomia crear(Gastronomia gastronomia) {
		gastronomia.setId(null);
		return gastronomiaRepository.save(gastronomia);
	}

	public Gastronomia actualizar(Long id, Gastronomia datos) {
		Gastronomia gastronomia = buscarPorId(id);
		gastronomia.setNombre(datos.getNombre());
		gastronomia.setDescripcion(datos.getDescripcion());
		gastronomia.setImagenUrl(datos.getImagenUrl());
		return gastronomiaRepository.save(gastronomia);
	}

	public void eliminar(Long id) {
		Gastronomia gastronomia = buscarPorId(id);
		gastronomiaRepository.delete(gastronomia);
	}
}
