package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Persona;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
public interface PersonaDao {

    Persona insertar(Persona persona);
    List<Persona> obtenerTodos();
    Persona obtenerPorId(int id);
    Persona actualizar(Persona persona);
    void borrar(Persona persona);

}
