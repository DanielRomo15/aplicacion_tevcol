package com.utc.aplicacion_tevcol.repository;

import com.utc.aplicacion_tevcol.entity.ActaBrigada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActaBrigadaRepository extends JpaRepository<ActaBrigada, Long> {
    long countByEstadoActaBrigada(Integer estadoActaBrigada);
}