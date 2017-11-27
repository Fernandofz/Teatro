package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Venta;

import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 20/11/2017
 */
public interface VentaDao {

    Venta insertar(Venta Venta);
    List<Venta> obtenerTodos();
    Venta obtenerPorId(int id);
    void borrar(Venta Venta);
}
