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
        test.update("INSERT INTO SUBVENCION (dni,numero,fecha_compra,fecha_vencimiento,cantidad_usos) VALUES(?,?,?,?,?)",
                new Object[] { subvencion.getDni(),subvencion.getNumero(), subvencion.getFechaCompra(), subvencion.getFechaVencimiento(),subvencion.getCantidadUsos()});
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
            subvencion.setDni(rs.getInt("dni"));
            subvencion.setNumero(rs.getInt("numero"));
            subvencion.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
            subvencion.setFechaCompra(rs.getDate("fecha_compra"));
            subvencion.setCantidadUsos(rs.getInt("cantidad_usos"));
            subvencion.setIdTipoSubvencion(rs.getInt("id_tipo_subvencion"));
            return subvencion;
        }
    }

    @Override
    public Subvencion obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Subvencion where id = ?", new Object[]{id});
        if (rs.next()){
            Subvencion subvencion = new Subvencion();
            subvencion.setDni(rs.getInt("dni"));
            subvencion.setNumero(rs.getInt("numero"));
            subvencion.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
            subvencion.setFechaCompra(rs.getDate("fecha_compra"));
            subvencion.setCantidadUsos(rs.getInt("cantidad_usos"));
            subvencion.setIdTipoSubvencion(rs.getInt("id_tipo_subvencion"));
            return subvencion;
        }else {
            return null;
        }
    }

    @Override
    public Subvencion actualizar(Subvencion subvencion) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("UPDATE SUBVENCION SET numero=?,fecha_compra=?,fecha_vencimiento=?,cantidad_usos=? WHERE dni=? AND numero=?",
                new Object[] {subvencion.getFechaCompra(), subvencion.getFechaVencimiento(),subvencion.getCantidadUsos(),subvencion.getDni(),subvencion.getNumero()});
        return subvencion;
    }

    @Override
    public void borrar(Subvencion subvencion) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM SUBVENCION WHERE dni=? AND numero=?",
                new Object[] { subvencion.getDni(), subvencion.getNumero()});
    }
    
}
