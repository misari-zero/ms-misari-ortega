package com.codigo.msmisariortega.infraestructure.dao;

import com.codigo.msmisariortega.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

}
