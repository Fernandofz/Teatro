package com.dabd2017.teatro.teatro.domain.dao;



import com.dabd2017.teatro.teatro.domain.entity.Espectador;

import java.util.List;

/**
 * Created by Fer on 17/11/2017.
 */
public interface EspectadorDao {

    Espectador insertar(Espectador espectador);
    List<Espectador> obtenerTodos();
    Espectador obtenerPorId(int id);
    void borrar(Espectador espectador);


}
