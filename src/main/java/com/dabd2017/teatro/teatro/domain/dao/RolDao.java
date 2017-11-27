package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Rol;

import java.util.List;

/**
 * Created by Fer on 18/11/2017.
 */
public interface RolDao {

    Rol insertar(Rol rol);
    List<Rol> obtenerTodos();
    Rol obtenerPorId(int id);
    void borrar(Rol rol);


}
