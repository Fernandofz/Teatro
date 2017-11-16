package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.TipoAbono;

import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 15/11/2017
 */
public interface TipoAbonoDao {
    TipoAbono insertar(TipoAbono tipoAbono);
    List<TipoAbono> obtenerTodos();
    TipoAbono obtenerPorId(int id);
    TipoAbono actualizar(TipoAbono tipoAbono);
    void borrar(TipoAbono tipoAbono);

}
