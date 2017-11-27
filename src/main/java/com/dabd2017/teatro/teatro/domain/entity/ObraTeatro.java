package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@Data
public class ObraTeatro {

    private Integer idObraTeatro;
    private Integer año;
    private String titulo;
    private Integer dniAutor;
    private String nacionalidad;
    private String genero;
    private String descripcion;
    private Boolean esTercera;
    private String image;

}
