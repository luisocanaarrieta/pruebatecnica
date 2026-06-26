package com.prueba.tipo_cambio_api.Service;

import com.prueba.tipo_cambio_api.Entity.Operacion;
import com.prueba.tipo_cambio_api.Repository.IOperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OperacionService implements  IOperacionService{

    @Autowired
    private IOperacionRepository repository;

    @Override
    public List<Operacion> listar() {
        return repository.findAll();
    }

//    @Override
//    public List<Operacion> listarPorEstado(String estado) {
//
//        return repository.findByIdEstado(estado);
//
//    }

    @Override
    public Operacion obtener(Long id) {

        return repository.findById(id).orElse(null);

    }

    @Override
    public Operacion guardar(Operacion op) {

        validar(op);

        return repository.save(op);

    }

    @Override
    public Operacion actualizar(Long id,Operacion op){

        op.setId(id);

        validar(op);

        return repository.save(op);

    }

    @Override
    public void eliminar(Long id){

        repository.deleteById(id);

    }

    private void validar(Operacion op){

        List<String> monedas =
                Arrays.asList("USD","PEN","EUR");

        if(!monedas.contains(op.getMonedaOrigen()))
            throw new RuntimeException("Moneda origen inválida");

        if(!monedas.contains(op.getMonedaDestino()))
            throw new RuntimeException("Moneda destino inválida");

        if(op.getMonedaOrigen().equals(op.getMonedaDestino()))
            throw new RuntimeException("Las monedas deben ser distintas");

        List<String> estados =
                Arrays.asList("PENDIENTE","EJECUTADA","ANULADA");

        if(!estados.contains(op.getEstado()))
            throw new RuntimeException("Estado inválido");

    }

    @Override
    public List<Operacion> listarPorEstado(String estado) {
        validarEstado(estado);
        return repository.findByEstado(estado);
    }

    @Override
    public Operacion actualizarEstado(Long id, String nuevoEstado) {
        Operacion op = repository.findById(id).orElse(null);
        if (op == null) throw new RuntimeException("Operación no encontrada");

        if ("ANULADA".equalsIgnoreCase(op.getEstado())) {
            throw new RuntimeException("No se puede modificar una operación ANULADA");
        }

        // validar nuevo estado
        validarEstado(nuevoEstado);

        op.setEstado(nuevoEstado);
        return repository.save(op);
    }

    private void validarEstado(String estado) {
        List<String> estados = Arrays.asList("PENDIENTE","EJECUTADA","ANULADA");
        if (estado == null || !estados.contains(estado)) {
            throw new RuntimeException("Estado inválido");
        }
    }
}
