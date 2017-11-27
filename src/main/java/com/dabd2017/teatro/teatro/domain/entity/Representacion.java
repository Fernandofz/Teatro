package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */

@Data
public class Representacion {
    private Integer idObraTeatro;
    private Integer idRepresentacion;
    private Date fechaAnuncio;
    private Date fechaObra;
    private Float precioCompra;
    private Float costoMantenimiento;
    private Float costoPreparacion;
    private Integer idCompañia;
}
