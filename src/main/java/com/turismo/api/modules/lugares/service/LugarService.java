package com.turismo.api.modules.lugares.service;

import com.turismo.api.exception.ResourceNotFoundException;
import com.turismo.api.modules.lugares.entity.Lugar;
import com.turismo.api.modules.lugares.repository.LugarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Modulo Lugares: agregar reglas de negocio de lugares solo en este servicio.
@Service
@RequiredArgsConstructor
public class LugarService {

	private final LugarRepository lugarRepository;

	public List<Lugar> listar() {
		return lugarRepository.findAll();
	}

	public Lugar buscarPorId(Long id) {
		return lugarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Lugar no encontrado con id: " + id));
	}

	public Lugar crear(Lugar lugar) {
		lugar.setId(null);
		return lugarRepository.save(lugar);
	}

	public Lugar actualizar(Long id, Lugar datos) {
		Lugar lugar = buscarPorId(id);
		lugar.setNombre(datos.getNombre());
		lugar.setDescripcion(datos.getDescripcion());
		lugar.setUbicacion(datos.getUbicacion());
		lugar.setImagenUrl(datos.getImagenUrl());
		lugar.setCategoria(datos.getCategoria());
		return lugarRepository.save(lugar);
	}

	public void eliminar(Long id) {
		Lugar lugar = buscarPorId(id);
		lugarRepository.delete(lugar);
	}
}
