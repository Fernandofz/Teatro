package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Compañia;

import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 14/11/2017
 */
public interface CompañiaDao {

    Compañia insertar(Compañia compañia);
    List<Compañia> obtenerTodos();
    Compañia obtenerPorId(int id);
    Compañia actualizar(Compañia compañia);
    void borrar(Compañia compañia);
}
