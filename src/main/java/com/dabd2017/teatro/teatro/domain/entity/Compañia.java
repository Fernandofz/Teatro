package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@Data
public class Compañia {

    private Integer id;
    private String nombre;
    private String direccion;
    private String prestigio;
    private Boolean esTercera;

}
