package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Persona;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
public class PersonaDaoImp implements PersonaDao {


    @Override
    public void insertar(Persona persona){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO PERSONA (dni,nombre,apellido,direccion,telefono) VALUES(?,?,?,?,?)",
        new Object[] { persona.getDni(),persona.getNombre(), persona.getApellido(), persona.getDireccion(),persona.getTelefono() });
    }

    @Override
    public List<Persona> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Persona", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Persona persona = new Persona();
            persona.setDni(rs.getInt("dni"));
            persona.setNombre(rs.getString("nombre"));
            persona.setApellido(rs.getString("apellido"));
            persona.setDireccion(rs.getString("direccion"));
            persona.setTelefono(rs.getString("telefono"));
            return persona;
        }
    }

    @Override
    public void borrar(Persona persona) {

    }
}
