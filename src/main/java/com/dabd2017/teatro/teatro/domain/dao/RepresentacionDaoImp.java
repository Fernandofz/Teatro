package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Representacion;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Fer on 17/11/2017.
 */
public class RepresentacionDaoImp implements RepresentacionDao {

    @Override
    public Representacion insertar(Representacion representacion){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO Representacion (id_obra_teatro,fecha_anuncio, fecha_obra, precio_compra, costo_mantenimiento, costo_preparacion, id_compañia) VALUES(?,?,?,?,?,?,?)",
                new Object[] { representacion.getIdObraTeatro(),representacion.getFechaAnuncio(),representacion.getFechaObra(),representacion.getPrecioCompra(),representacion.getCostoMantenimiento(),representacion.getCostoPreparacion(),representacion.getIdCompañia()});
        return obtenerUltimo(representacion.getIdObraTeatro());
    }

    @Override
    public List<Representacion> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Representacion", new PersonaMapper());
    }

    @Override
    public List<Representacion> obtenerTodosActuales() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Representacion where fecha_obra > NOW()", new PersonaMapper());
    }

    @Override
    public Representacion obtenerUltimo(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from representacion where id_representacion = (select max(id_representacion) from representacion) and id_obra_teatro = "+id);
        if (rs.next()){
            Representacion representacion = new Representacion();
            representacion.setIdRepresentacion(rs.getInt("id_representacion"));
            representacion.setIdObraTeatro(rs.getInt("id_obra_teatro"));
            representacion.setFechaAnuncio(rs.getDate("fecha_anuncio"));
            representacion.setFechaObra(rs.getDate("fecha_obra"));
            representacion.setCostoMantenimiento(rs.getFloat("costo_mantenimiento"));
            representacion.setCostoPreparacion(rs.getFloat("costo_preparacion"));
            representacion.setPrecioCompra(rs.getFloat("precio_compra"));
            return representacion;
        }else {
            return null;
        }
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Representacion representacion = new Representacion();
            representacion.setIdRepresentacion(rs.getInt("id_representacion"));
            representacion.setIdObraTeatro(rs.getInt("id_obra_teatro"));
            representacion.setFechaAnuncio(rs.getDate("fecha_anuncio"));
            representacion.setFechaObra(rs.getDate("fecha_obra"));
            representacion.setCostoMantenimiento(rs.getFloat("costo_mantenimiento"));
            representacion.setCostoPreparacion(rs.getFloat("costo_preparacion"));
            representacion.setPrecioCompra(rs.getFloat("precio_compra"));
            representacion.setIdCompañia(rs.getInt("id_compañia"));
            return representacion;
        }
    }

    @Override
    public Representacion obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Representacion where id = ?", new Object[]{id});
        if (rs.next()){
            Representacion representacion = new Representacion();
            representacion.setIdRepresentacion(rs.getInt("id_representacion"));
            representacion.setIdObraTeatro(rs.getInt("id_obra_teatro"));
            representacion.setFechaAnuncio(rs.getDate("fecha_anuncio"));
            representacion.setFechaObra(rs.getDate("fecha_obra"));
            representacion.setCostoMantenimiento(rs.getFloat("costo_mantenimiento"));
            representacion.setCostoPreparacion(rs.getFloat("costo_preparacion"));
            representacion.setPrecioCompra(rs.getFloat("precio_compra"));
            return representacion;
        }else {
            return null;
        }
    }

    @Override
    public void borrar(Representacion Representacion) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM Representacion WHERE id_Representacion=? AND id_obra_teatro=?",
                new Object[] { Representacion.getIdRepresentacion(), Representacion.getIdObraTeatro()});
    }
}
