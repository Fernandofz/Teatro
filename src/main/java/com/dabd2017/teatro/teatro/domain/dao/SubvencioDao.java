package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Subvencion;

import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 15/11/2017
 */
public interface SubvencioDao {

    Subvencion insertar(Subvencion subvencion);
    List<Subvencion> obtenerTodos();
    Subvencion obtenerPorId(int id);
    Subvencion actualizar(Subvencion subvencion);
    void borrar(Subvencion subvencion);


}
