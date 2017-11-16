package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Abono;
import com.dabd2017.teatro.teatro.domain.entity.Persona;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
public interface AbonoDao {
    Abono insertar(Abono abono);
    List<Abono> obtenerTodos();
    Abono obtenerPorId(int id);
    Abono actualizar(Abono abono);
    void borrar(Abono abono);
}
