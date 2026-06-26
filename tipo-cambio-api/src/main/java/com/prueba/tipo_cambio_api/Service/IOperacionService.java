package com.prueba.tipo_cambio_api.Service;

import com.prueba.tipo_cambio_api.Entity.Operacion;

import java.util.List;

public interface IOperacionService {
    List<Operacion> listar();

    List<Operacion> listarPorEstado(String estado);
    Operacion actualizarEstado(Long id, String nuevoEstado);
    Operacion obtener(Long id);

    Operacion guardar(Operacion operacion);

    Operacion actualizar(Long id,Operacion operacion);

    void eliminar(Long id);
}
