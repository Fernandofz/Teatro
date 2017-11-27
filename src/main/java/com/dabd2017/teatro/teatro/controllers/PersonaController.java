package com.dabd2017.teatro.teatro.controllers;

import com.dabd2017.teatro.teatro.domain.dao.AbonoDao;
import com.dabd2017.teatro.teatro.domain.dao.AbonoDaoImp;
import com.dabd2017.teatro.teatro.domain.dao.PersonaDao;
import com.dabd2017.teatro.teatro.domain.dao.TipoAbonoDao;
import com.dabd2017.teatro.teatro.domain.dao.TipoAbonoDaoImp;
import com.dabd2017.teatro.teatro.domain.entity.Abono;
import com.dabd2017.teatro.teatro.domain.entity.Persona;
import com.dabd2017.teatro.teatro.domain.entity.TipoAbono;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@RestController
public class PersonaController {

    private final PersonaDao personaDao;

    private final AbonoDao abonoDao = new AbonoDaoImp();

    private final TipoAbonoDao tipoAbonoDao = new TipoAbonoDaoImp();

    private JdbcTemplate driver = DriverDB.getDriver();

    public PersonaController(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }



    @PostMapping("/persona")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
            if(persona.getDni()==null){
                persona.setDni(personaDao.obtenerMaxAutoId() + 1);
            }
            personaDao.insertar(persona);
        return ResponseEntity.ok(persona);
    }

    @GetMapping("/personas")
    public List<Map<String, Object>> obtenerPersonas(@RequestParam String nombre){
        String query;
        if(nombre==""){
            query  = "select * from persona";
            return driver.queryForList(query);
        }
        else {
            query  = "select * from persona where nombre like '%"+nombre+"%' or apellido like '%"+nombre+"%' or dni like '"+nombre+"%'";
            return driver.queryForList(query);
        }

    }

    @GetMapping("/personasabonadas")
    public List<Map<String, Object>> obtenerAbonosValidos(@RequestParam String nombre){
        String query;
        if(nombre==""){
            query  = "select p.dni, p.nombre, p.apellido, a.numero from persona p inner join abono a on a.dni = p.dni inner join tipoabono ta on ta.id_tipo_abono = a.id_tipo_abono where a.cantidad_usos > 0 and a.fecha_vencimiento >= now()";
            return driver.queryForList(query);
        }
        else {
            query  = "select p.dni, p.nombre, p.apellido, a.numero from persona p inner join abono a on a.dni = p.dni inner join tipoabono ta on ta.id_tipo_abono = a.id_tipo_abono where a.cantidad_usos > 0 and a.fecha_vencimiento >= now() and p.nombre like '%"+nombre+"%'";
            return driver.queryForList(query);
        }
    }

    @PostMapping("/abono")
    public ResponseEntity<Abono> crearAbono(@RequestBody Abono abono) {
        abonoDao.insertar(abono);
        return ResponseEntity.ok(abono);
    }

    @GetMapping("/tipoabono")
    public ResponseEntity<List<TipoAbono>> tipoAbono(){
        return ResponseEntity.ok(tipoAbonoDao.obtenerTodos());
    }

}
