package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Rol;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Fer on 18/11/2017.
 */
public class RolDaoImp implements RolDao {

    @Override
    public Rol insertar(Rol rol){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO ROL (id_participa,dni,id_representacion,id_obra_teatro,rol) VALUES(?,?,?,?,?)",
                new Object[] { rol.getIdParticipa(),rol.getDni(),rol.getIdRepresentacion(),rol.getIdObraTeatro(),rol.getRol()});
        return rol;
    }

    @Override
    public List<Rol> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Rol", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rol rol = new Rol();
            rol.setIdParticipa(rs.getInt("id_participa"));
            rol.setDni(rs.getInt("id_dni"));
            rol.setIdRepresentacion(rs.getInt("id_representacion"));
            rol.setIdObraTeatro(rs.getInt("id_obra_teatro"));
            rol.setRol(rs.getString("rol"));
            return rol;
        }
    }

    @Override
    public Rol obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Rol where id = ?", new Object[]{id});
        if (rs.next()){
            Rol rol = new Rol();
            rol.setIdParticipa(rs.getInt("id_participa"));
            rol.setDni(rs.getInt("id_dni"));
            rol.setIdRepresentacion(rs.getInt("id_representacion"));
            rol.setIdObraTeatro(rs.getInt("id_obra_teatro"));
            rol.setRol(rs.getString("rol"));
            return rol;
        }else {
            return null;
        }
    }

    @Override
    public void borrar(Rol rol) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM ROL WHERE id_participa=? AND dni=? AND id_representacion=? AND id_obra_teatro=? AND rol=?",
                new Object[] { rol.getIdParticipa(),rol.getDni(),rol.getIdRepresentacion(),rol.getIdObraTeatro(),rol.getRol() });
    }
    
}
