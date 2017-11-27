package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Entrada;

import java.util.List;

/**
 * Created by Fer on 18/11/2017.
 */
public interface EntradaDao {

    Entrada insertar(Entrada entrada);
    List<Entrada> obtenerTodos();
    Entrada obtenerPorId(int id);
    Entrada actualizar(Entrada entrada);
    void borrar(Entrada entrada);

}
