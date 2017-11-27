package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Persona;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
public class PersonaDaoImp implements PersonaDao {


    @Override
    public Integer obtenerMaxAutoId() {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select max(dni) as dni from Persona where dni < 20000");
        if (rs.next()){
            return rs.getInt("dni");
        }else {
            return null;
        }
    }

    @Override
    public Persona insertar(Persona persona){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO PERSONA (dni,nombre,apellido,direccion,telefono) VALUES(?,?,?,?,?)",
        new Object[] { persona.getDni(),persona.getNombre(), persona.getApellido(), persona.getDireccion(),persona.getTelefono() });
        return persona;
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
    public Persona obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Persona where id = ?", new Object[]{id});
        if (rs.next()){
            Persona persona = new Persona();
            persona.setDni(rs.getInt("dni"));
            persona.setNombre(rs.getString("nombre"));
            persona.setApellido(rs.getString("apellido"));
            persona.setDireccion(rs.getString("direccion"));
            persona.setTelefono(rs.getString("telefono"));
            return persona;
        }else {
            return null;
        }
    }

    @Override
    public Persona actualizar(Persona persona) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("UPDATE Persona SET nombre=?,apellido=?,direccion=?,telefono=? WHERE dni=?",
                new Object[] { persona.getNombre(),persona.getApellido(), persona.getDireccion(), persona.getTelefono(), persona.getDni()});
        return persona;
    }

    @Override
    public void borrar(Persona persona) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM Persona WHERE dni=?",
                new Object[] { persona.getDni()});
    }
}
