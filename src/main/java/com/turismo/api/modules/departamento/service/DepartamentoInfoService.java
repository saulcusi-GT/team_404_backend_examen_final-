package com.turismo.api.modules.departamento.service;

import com.turismo.api.exception.ResourceNotFoundException;
import com.turismo.api.modules.departamento.entity.Atractivo;
import com.turismo.api.modules.departamento.entity.DepartamentoInfo;
import com.turismo.api.modules.departamento.repository.DepartamentoInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoInfoService {

	private final DepartamentoInfoRepository departamentoInfoRepository;

	public List<DepartamentoInfo> listar() {
		return departamentoInfoRepository.findAll();
	}

	public DepartamentoInfo buscarPorId(Long id) {
		return departamentoInfoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con id: " + id));
	}

	public DepartamentoInfo obtenerPrincipal() {
		return departamentoInfoRepository.findAll().stream()
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Aun no hay informacion de departamento cargada"));
	}

	public DepartamentoInfo crear(DepartamentoInfo departamento) {
		departamento.setId(null);
		return departamentoInfoRepository.save(departamento);
	}

	public DepartamentoInfo actualizar(Long id, DepartamentoInfo datos) {
		DepartamentoInfo departamento = buscarPorId(id);
		departamento.setTitulo(datos.getTitulo());
		departamento.setIntroduccion(datos.getIntroduccion());
		return departamentoInfoRepository.save(departamento);
	}

	public void eliminar(Long id) {
		DepartamentoInfo departamento = buscarPorId(id);
		departamentoInfoRepository.delete(departamento);
	}

	public DepartamentoInfo agregarAtractivo(Long id, Atractivo atractivo) {
		DepartamentoInfo departamento = buscarPorId(id);
		departamento.agregarAtractivo(atractivo);
		return departamentoInfoRepository.save(departamento);
	}

	public DepartamentoInfo actualizarAtractivo(Long departamentoId, Long atractivoId, Atractivo datos) {
		DepartamentoInfo departamento = buscarPorId(departamentoId);
		Atractivo atractivo = departamento.getAtractivos().stream()
				.filter(a -> a.getId().equals(atractivoId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Atractivo no encontrado con id: " + atractivoId));

		atractivo.setNombre(datos.getNombre());
		atractivo.setDescripcion(datos.getDescripcion());
		atractivo.setOrden(datos.getOrden());

		return departamentoInfoRepository.save(departamento);
	}

	public DepartamentoInfo eliminarAtractivo(Long departamentoId, Long atractivoId) {
		DepartamentoInfo departamento = buscarPorId(departamentoId);
		boolean eliminado = departamento.getAtractivos().removeIf(a -> a.getId().equals(atractivoId));

		if (!eliminado) {
			throw new ResourceNotFoundException("Atractivo no encontrado con id: " + atractivoId);
		}

		return departamentoInfoRepository.save(departamento);
	}
}
