package com.prueba.tipo_cambio_api.Dto;

import jakarta.validation.constraints.NotBlank;

public class EstadoPatchDTO {
    @NotBlank
    private String estado;

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
