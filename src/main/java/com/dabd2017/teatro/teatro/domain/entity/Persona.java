package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@Data
public class Persona {

    private Integer dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

}
