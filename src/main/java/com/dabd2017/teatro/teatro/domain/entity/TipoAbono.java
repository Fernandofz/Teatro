package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

/**
 * @author Fernando Fernandez
 * @date 15/11/2017
 */
@Data
public class TipoAbono {
    private Integer idTipoAbono;
    private String nombre;
    private Integer cantidadUsos;
    private Float precio;

}
