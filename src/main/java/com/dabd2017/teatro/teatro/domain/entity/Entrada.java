package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@Data
public class Entrada {

    private int numero;
    private int butaca;
    private Date fecha;
    private String espectador;
    private int dni;
    private int numeroAbnono;
    private int idRepresntacion;
}
