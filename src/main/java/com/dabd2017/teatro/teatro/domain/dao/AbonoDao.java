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


    void insertar(Abono abono) throws SQLException;
    List<Abono> obtenerTodos();
    void borrar(Abono abono);


}
