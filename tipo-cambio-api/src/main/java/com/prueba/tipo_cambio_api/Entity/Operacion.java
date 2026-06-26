package com.prueba.tipo_cambio_api.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name ="fx_operacion")
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "moneda_origen")
    private String monedaOrigen;

    @Column(name = "moneda_destino")
    private String monedaDestino;

    @Column(name = "monto_origen")
    private BigDecimal montoOrigen;

    @Column(name = "tipo_cambio")
    private BigDecimal tipoCambio;

    @Column(name = "monto_destino", insertable = false, updatable = false)
    private BigDecimal montoDestino;

    @Column(name = "fecha_operacion")
    private LocalDate fechaOperacion;

    private String estado;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
