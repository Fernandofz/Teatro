package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Venta;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 20/11/2017
 */
public class VentaDaoImp implements VentaDao {

    @Override
    public Venta insertar(Venta venta){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO Venta (id_compañia,precio,fecha,id_obra_teatro) VALUES(?,?,?,?)",
                new Object[] {venta.getCompañia(),venta.getPrecio(), venta.getFecha(),venta.getObraTeatro()});
        return venta;
    }

    @Override
    public List<Venta> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Venta", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Venta venta = new Venta();
            venta.setNroVenta(rs.getInt("nro_venta"));
            venta.setObraTeatro(rs.getInt("id_obra_teatro"));
            venta.setCompañia(rs.getInt("id_compañia"));
            venta.setPrecio(rs.getFloat("precio"));
            venta.setFecha(rs.getDate("fecha"));
            return venta;
        }
    }

    @Override
    public Venta obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Venta where nro_venta = ?", new Object[]{id});
        if (rs.next()){
            Venta venta = new Venta();
            venta.setNroVenta(rs.getInt("nro_venta"));
            venta.setObraTeatro(rs.getInt("id_obra_teatro"));
            venta.setCompañia(rs.getInt("id_compañia"));
            venta.setPrecio(rs.getFloat("precio"));
            venta.setFecha(rs.getDate("fecha"));
            return venta;
        }else {
            return null;
        }
    }

    @Override
    public void borrar(Venta venta) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM Venta WHERE nro_venta=?",
                new Object[] { venta.getNroVenta()});
    }
    
}
