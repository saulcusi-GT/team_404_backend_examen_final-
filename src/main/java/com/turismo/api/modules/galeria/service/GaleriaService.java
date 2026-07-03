package com.turismo.api.modules.galeria.service;

import com.turismo.api.exception.ResourceNotFoundException;
import com.turismo.api.modules.galeria.entity.Galeria;
import com.turismo.api.modules.galeria.repository.GaleriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Módulo Galería: agregar reglas de negocio de galería solo en este servicio.
@Service
@RequiredArgsConstructor
public class GaleriaService {

    private final GaleriaRepository galeriaRepository;

    public List<Galeria> listar() {
        return galeriaRepository.findAll();
    }

    public Galeria buscarPorId(Long id) {
        return galeriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Imagen de galería no encontrada con id: " + id
                ));
    }

    public Galeria crear(Galeria galeria) {
        galeria.setId(null);
        return galeriaRepository.save(galeria);
    }

    public Galeria actualizar(Long id, Galeria datos) {
        Galeria galeria = buscarPorId(id);

        galeria.setTitulo(datos.getTitulo());
        galeria.setImagenUrl(datos.getImagenUrl());
        galeria.setDescripcion(datos.getDescripcion());

        return galeriaRepository.save(galeria);
    }

    public void eliminar(Long id) {
        Galeria galeria = buscarPorId(id);
        galeriaRepository.delete(galeria);
    }
}