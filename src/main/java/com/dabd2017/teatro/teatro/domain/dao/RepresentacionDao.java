package com.dabd2017.teatro.teatro.domain.dao;



import com.dabd2017.teatro.teatro.domain.entity.Representacion;

import java.util.List;

/**
 * Created by Fer on 17/11/2017.
 */
public interface RepresentacionDao {

    Representacion insertar(Representacion representacion);
    List<Representacion> obtenerTodos();
    List<Representacion> obtenerTodosActuales();
    Representacion obtenerUltimo(int id);
    Representacion obtenerPorId(int id);
    void borrar(Representacion representacion);
    
}
