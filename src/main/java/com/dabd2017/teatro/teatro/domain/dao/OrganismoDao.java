package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Organismo;

import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 15/11/2017
 */
public interface OrganismoDao {

    Organismo insertar(Organismo organismo);
    List<Organismo> obtenerTodos();
    Organismo obtenerPorId(int id);
    Organismo actualizar(Organismo organismo);
    void borrar(Organismo organismo);
    
}
