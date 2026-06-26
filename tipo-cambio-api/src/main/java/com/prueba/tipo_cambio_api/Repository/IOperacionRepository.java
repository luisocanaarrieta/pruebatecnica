package com.prueba.tipo_cambio_api.Repository;

import com.prueba.tipo_cambio_api.Entity.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOperacionRepository extends JpaRepository<Operacion, Long> {
    List<Operacion> findByEstado(String estado);}
