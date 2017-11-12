package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Abono;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
public class AbonoDaoImp implements AbonoDao {

    @Override
    public void insertar(Abono abono) throws SQLException {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO ABONO (dni,numero,fecha_compra,fecha_vencimiento,cantidad_usos) VALUES(?,?,?,?,?)",
        new Object[] { abono.getDni(),abono.getNumero(), abono.getFechaCompra(), abono.getFechaVencimiento(),abono.getCantidadUsos()});
    }

    @Override
    public List<Abono> obtenerTodos() {
        return null;
    }

    @Override
    public void borrar(Abono abono) {

    }
}
