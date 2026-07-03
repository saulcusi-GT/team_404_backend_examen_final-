package com.turismo.api.modules.lugares.service;

import com.turismo.api.exception.ResourceNotFoundException;
import com.turismo.api.modules.lugares.entity.Lugar;
import com.turismo.api.modules.lugares.repository.LugarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Módulo Lugares: reglas de negocio del CRUD de lugares.
@Service
@RequiredArgsConstructor
public class LugarService {

    private final LugarRepository lugarRepository;

    // LISTAR TODOS
    public List<Lugar> listar() {
        return lugarRepository.findAll();
    }

    // BUSCAR POR ID
    public Lugar buscarPorId(Long id) {
        return lugarRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lugar no encontrado con id: " + id));
    }

    // CREAR
    public Lugar crear(Lugar lugar) {
        lugar.setId(null);
        return lugarRepository.save(lugar);
    }

    // ACTUALIZAR
    public Lugar actualizar(Long id, Lugar datos) {
        Lugar lugar = buscarPorId(id);

        lugar.setNombre(datos.getNombre());
        lugar.setDescripcion(datos.getDescripcion());
        lugar.setUbicacion(datos.getUbicacion());
        lugar.setImagenUrl(datos.getImagenUrl());
        lugar.setCategoria(datos.getCategoria());

        return lugarRepository.save(lugar);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        Lugar lugar = buscarPorId(id);
        lugarRepository.delete(lugar);
    }
}