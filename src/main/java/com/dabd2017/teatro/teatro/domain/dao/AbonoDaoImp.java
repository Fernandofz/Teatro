package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Abono;
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
public class AbonoDaoImp implements AbonoDao {

    @Override
    public Abono insertar(Abono abono){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO ABONO (dni,numero,fecha_compra,fecha_vencimiento,cantidad_usos) VALUES(?,?,?,?,?)",
        new Object[] { abono.getDni(),abono.getNumero(), abono.getFechaCompra(), abono.getFechaVencimiento(),abono.getCantidadUsos()});
        return abono;
    }

    @Override
    public List<Abono> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Abono", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Abono abono = new Abono();
            abono.setDni(rs.getInt("dni"));
            abono.setNumero(rs.getInt("numero"));
            abono.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
            abono.setFechaCompra(rs.getDate("fecha_compra"));
            abono.setCantidadUsos(rs.getInt("cantidad_usos"));
            abono.setIdTipoAbono(rs.getInt("id_tipo_abono"));
            return abono;
        }
    }

    @Override
    public Abono obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Abono where id = ?", new Object[]{id});
        if (rs.next()){
            Abono abono = new Abono();
            abono.setDni(rs.getInt("dni"));
            abono.setNumero(rs.getInt("numero"));
            abono.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
            abono.setFechaCompra(rs.getDate("fecha_compra"));
            abono.setCantidadUsos(rs.getInt("cantidad_usos"));
            abono.setIdTipoAbono(rs.getInt("id_tipo_abono"));
            return abono;
        }else {
            return null;
        }
    }

    @Override
    public Abono actualizar(Abono abono) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("UPDATE ABONO SET numero=?,fecha_compra=?,fecha_vencimiento=?,cantidad_usos=? WHERE dni=? AND numero=?",
                new Object[] {abono.getFechaCompra(), abono.getFechaVencimiento(),abono.getCantidadUsos(),abono.getDni(),abono.getNumero()});
        return abono;
    }

    @Override
    public void borrar(Abono abono) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM ABONO WHERE dni=? AND numero=?",
                new Object[] { abono.getDni(), abono.getNumero()});
    }
}
