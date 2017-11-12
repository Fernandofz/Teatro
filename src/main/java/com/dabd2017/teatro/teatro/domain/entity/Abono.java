package com.dabd2017.teatro.teatro.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Fernando Fernandez
 * @date 11/11/2017
 */
@Data
public class Abono {

private Integer dni;
private Integer numero;
private Date fechaCompra;
private Date fechaVencimiento;
private int cantidadUsos;

}
