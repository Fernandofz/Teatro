package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Participa;

import java.util.List;

/**
 * Created by Fer on 17/11/2017.
 */
public interface ParticipaDao {

    Participa insertar(Participa participa);
    List<Participa> obtenerTodos();
    Participa obtenerPorId(int id);
    void borrar(Participa participa);
}
