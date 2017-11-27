package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Subvencion;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 16/11/2017
 */
public class SubvencionDaoImp implements SubvencioDao {

    @Override
    public Subvencion insertar(Subvencion subvencion){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO SUBVENCION (id_obra_teatro,id_organismo,fecha,aporte) VALUES(?,?,?,?)",
                new Object[] { subvencion.getObraTeatro(),subvencion.getOrganismo(), subvencion.getFecha(), subvencion.getAporte()});
        return subvencion;
    }

    @Override
    public List<Subvencion> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Subvencion", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Subvencion subvencion = new Subvencion();
            subvencion.setIdSubvencion(rs.getInt("id_subvencion"));
            subvencion.setObraTeatro(rs.getInt("id_obra_teatro"));
            subvencion.setOrganismo(rs.getInt("id_organismo"));
            subvencion.setFecha(rs.getDate("fecha"));
            subvencion.setAporte(rs.getFloat("aporte"));
            return subvencion;
        }
    }

    @Override
    public Subvencion obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Subvencion where id = ?", new Object[]{id});
        if (rs.next()){
            Subvencion subvencion = new Subvencion();
            subvencion.setIdSubvencion(rs.getInt("id_subvencion"));
            subvencion.setObraTeatro(rs.getInt("id_obra_teatro"));
            subvencion.setOrganismo(rs.getInt("id_organismo"));
            subvencion.setFecha(rs.getDate("fecha"));
            subvencion.setAporte(rs.getFloat("aporte"));
            return subvencion;
        }else {
            return null;
        }
    }

    @Override
    public void borrar(Subvencion subvencion) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM SUBVENCION WHERE id_subvencion=? AND id_obra_teatro=? AND id_organismo=?",
                new Object[] { subvencion.getIdSubvencion(), subvencion.getObraTeatro(),subvencion.getOrganismo()});
    }
    
}
