package com.turismo.api.modules.departamento.repository;

import com.turismo.api.modules.departamento.entity.DepartamentoInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartamentoInfoRepository extends JpaRepository<DepartamentoInfo, Long> {

	@Override
	@EntityGraph(attributePaths = "atractivos")
	List<DepartamentoInfo> findAll();

	@Override
	@EntityGraph(attributePaths = "atractivos")
	Optional<DepartamentoInfo> findById(Long id);

	@EntityGraph(attributePaths = "atractivos")
	Optional<DepartamentoInfo> findFirstByOrderByIdAsc();
}
