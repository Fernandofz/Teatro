package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@Data
public class Entrada {

    private Integer idRepresentacion;
    private Integer butaca;
    private Date fecha;
    private Integer espectador;
    private Integer dni;
    private Integer numeroAbono;
    private Integer dniIndividual;
    private Boolean ocupado;

}
