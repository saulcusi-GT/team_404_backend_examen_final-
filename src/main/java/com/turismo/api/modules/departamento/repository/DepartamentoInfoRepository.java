package com.turismo.api.modules.departamento.repository;

import com.turismo.api.modules.departamento.entity.DepartamentoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoInfoRepository extends JpaRepository<DepartamentoInfo, Long> {
}
