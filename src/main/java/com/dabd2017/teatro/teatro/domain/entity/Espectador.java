package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

/**
 * Created by Fer on 17/11/2017.
 */
@Data
public class Espectador {

    private Integer idEspectador;
    private String tipo;
    private Integer idRepresentacion;
    private Float precio;

}
