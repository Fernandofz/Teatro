package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@Data
public class Venta {

    private int nroVenta;
    private float precio;
    private Date fecha;
    private Integer obraTeatro;
    private Integer compañia;

}
