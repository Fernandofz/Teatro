package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@Data
public class Subvencion {

    private Integer idSubvencion;
    private ObraTeatro obraTeatro;
    private Organismo organismo;
    private Float aporte;
    private Date fecha;

}
