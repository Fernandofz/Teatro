package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.TipoAbono;
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
public class TipoAbonoDaoImp implements TipoAbonoDao {


    @Override
    public TipoAbono insertar(TipoAbono tipoAbono){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO TipoAbono (nombre,cantidad,precio) VALUES(?,?,?)",
                new Object[] {tipoAbono.getNombre(),tipoAbono.getCantidadUsos(), tipoAbono.getPrecio()});
        return tipoAbono;
    }

    @Override
    public List<TipoAbono> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from TipoAbono", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            TipoAbono tipoAbono = new TipoAbono();
            tipoAbono.setIdTipoAbono(rs.getInt("id_tipoAbono"));
            tipoAbono.setNombre(rs.getString("nombre"));
            tipoAbono.setCantidadUsos(rs.getInt("Cantidad"));
            tipoAbono.setPrecio(rs.getFloat("precio"));
            return tipoAbono;
        }
    }

    @Override
    public TipoAbono obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from TipoAbono where id = ?", new Object[]{id});
        if (rs.next()){
            TipoAbono tipoAbono = new TipoAbono();
            tipoAbono.setIdTipoAbono(rs.getInt("id_tipoAbono"));
            tipoAbono.setNombre(rs.getString("nombre"));
            tipoAbono.setCantidadUsos(rs.getInt("Cantidad"));
            tipoAbono.setPrecio(rs.getFloat("precio"));
            return tipoAbono;
        }else {
            return null;
        }
    }

    @Override
    public TipoAbono actualizar(TipoAbono tipoAbono) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("UPDATE TipoAbono SET nombre=?,cantidad=?,precio=? WHERE id_tipoAbono=?",
                new Object[] { tipoAbono.getNombre(),tipoAbono.getCantidadUsos(), tipoAbono.getPrecio(), tipoAbono.getIdTipoAbono()});
        return tipoAbono;
    }

    @Override
    public void borrar(TipoAbono tipoAbono) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM TipoAbono WHERE id_tipoAbono=?",
                new Object[] { tipoAbono.getIdTipoAbono()});
    }
}
