package com.dabd2017.teatro.teatro.controllers;

import com.dabd2017.teatro.teatro.domain.dao.PersonaDao;
import com.dabd2017.teatro.teatro.domain.entity.Persona;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@RestController
public class PersonaController {

    private final PersonaDao personaDao;

    public PersonaController(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }


    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> obtenerPersonas(){
        List<Persona> personas = personaDao.obtenerTodos();
        return ResponseEntity.ok(personas);
    }

    @PostMapping("/persona")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) throws SQLException {
        try{
            personaDao.insertar(persona);
        } catch (SQLException e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(persona);
    }
}
