package com.turismo.api.modules.contacto.service;

import com.turismo.api.exception.ResourceNotFoundException;
import com.turismo.api.modules.contacto.entity.Contacto;
import com.turismo.api.modules.contacto.repository.ContactoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Modulo Contacto: agregar reglas de negocio de contacto solo en este servicio.
@Service
@RequiredArgsConstructor
public class ContactoService {

	private final ContactoRepository contactoRepository;

	public List<Contacto> listar() {
		return contactoRepository.findAll();
	}

	public Contacto buscarPorId(Long id) {
		return contactoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contacto no encontrado con id: " + id));
	}

	public Contacto crear(Contacto contacto) {
		contacto.setId(null);
		return contactoRepository.save(contacto);
	}

	public Contacto actualizar(Long id, Contacto datos) {
		Contacto contacto = buscarPorId(id);
		contacto.setNombre(datos.getNombre());
		contacto.setCorreo(datos.getCorreo());
		contacto.setMensaje(datos.getMensaje());
		return contactoRepository.save(contacto);
	}

	public void eliminar(Long id) {
		Contacto contacto = buscarPorId(id);
		contactoRepository.delete(contacto);
	}
}
