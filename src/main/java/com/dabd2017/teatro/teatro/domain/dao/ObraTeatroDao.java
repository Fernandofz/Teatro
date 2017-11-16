package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.ObraTeatro;

import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 15/11/2017
 */
public interface ObraTeatroDao {
    ObraTeatro insertar(ObraTeatro obraTeatro);
    List<ObraTeatro> obtenerTodos();
    ObraTeatro obtenerPorId(int id);
    ObraTeatro actualizar(ObraTeatro obraTeatro);
    void borrar(ObraTeatro obraTeatro);
}
