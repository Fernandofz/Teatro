package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Compañia;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 15/11/2017
 */
public class CompañiaDaoImp implements CompañiaDao{


    @Override
    public Compañia insertar(Compañia compañia){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO COMPAÑIA (nombre,direccion,prestigio,es_tercera) VALUES(?,?,?,?)",
                new Object[] { compañia.getNombre(),compañia.getDireccion(), compañia.getPrestigio(), compañia.getEsTercera()});
        return compañia;
    }

    @Override
    public List<Compañia> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Compañia", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Compañia compañia = new Compañia();
            compañia.setId(rs.getInt("id_compañia"));
            compañia.setNombre(rs.getString("nombre"));
            compañia.setDireccion(rs.getString("direccion"));
            compañia.setPrestigio(rs.getString("prestigio"));
            compañia.setEsTercera(rs.getBoolean("es_tercera"));
            return compañia;
        }
    }

    @Override
    public Compañia obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Compañia where id = ?", new Object[]{id});
        if (rs.next()){
            Compañia compañia = new Compañia();
            compañia.setId(rs.getInt("id_compañia"));
            compañia.setNombre(rs.getString("nombre"));
            compañia.setDireccion(rs.getString("direccion"));
            compañia.setPrestigio(rs.getString("prestigio"));
            compañia.setEsTercera(rs.getBoolean("es_tercera"));
            return compañia;
        }else {
            return null;
        }
    }

    @Override
    public Compañia actualizar(Compañia compañia) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("UPDATE COMPAÑIA SET nombre=?,direccion=?,prestigio=?,es_tercera=? WHERE id_compañia=?",
                new Object[] { compañia.getDireccion(), compañia.getPrestigio(), compañia.getEsTercera(),compañia.getNombre()});
        return compañia;
    }

    @Override
    public void borrar(Compañia compañia) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM COMPAÑIA WHERE id_compañia=?",
                new Object[] { compañia.getId()});
    }
    
}
