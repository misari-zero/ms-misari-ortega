package com.codigo.msmisariortega.infraestructure.dao;

import com.codigo.msmisariortega.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

}
