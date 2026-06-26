package com.prueba.tipo_cambio_api.Controller;

import com.prueba.tipo_cambio_api.Dto.EstadoPatchDTO;
import com.prueba.tipo_cambio_api.Entity.Operacion;
import com.prueba.tipo_cambio_api.Service.IOperacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fx")
@CrossOrigin("*")
public class OperacionController {

    private final IOperacionService service;

    public OperacionController(IOperacionService service) {
        this.service = service;
    }

    @PostMapping("/operaciones")
    @ResponseStatus(HttpStatus.CREATED)
    public Operacion registrar(@RequestBody @Valid Operacion op) {
        return service.guardar(op);
    }

    @GetMapping("/operaciones")
    public List<Operacion> listar(@RequestParam(name = "estado", required = false) String estado) {
        if (estado == null || estado.isBlank()) return service.listar();
        return service.listarPorEstado(estado);
    }

    @PatchMapping("/{id}/estado")
    public Operacion actualizarEstado(@PathVariable Long id, @RequestBody @Valid EstadoPatchDTO dto) {
        return service.actualizarEstado(id, dto.getEstado());
    }
}