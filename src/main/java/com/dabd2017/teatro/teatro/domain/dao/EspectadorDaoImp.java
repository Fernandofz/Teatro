package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Espectador;
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
public class EspectadorDaoImp implements  EspectadorDao{

    @Override
    public Espectador insertar(Espectador espectador){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO ESPECTADOR (id_representacion,tipo,precio) VALUES(?,?,?)",
                new Object[] { espectador.getIdRepresentacion(),espectador.getTipo(), espectador.getPrecio()});
        return espectador;
    }

    @Override
    public List<Espectador> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Espectador", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Espectador espectador = new Espectador();
            espectador.setIdEspectador(rs.getInt("id_espectador"));
            espectador.setTipo(rs.getString("tipo"));
            espectador.setPrecio(rs.getFloat("precio"));
            return espectador;
        }
    }

    @Override
    public Espectador obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Espectador where id_espectador = ?", new Object[]{id});
        if (rs.next()){
            Espectador espectador = new Espectador();
            espectador.setIdEspectador(rs.getInt("id_espectador"));
            espectador.setTipo(rs.getString("tipo"));
            espectador.setPrecio(rs.getFloat("precio"));
            return espectador;
        }else {
            return null;
        }
    }

    @Override
    public void borrar(Espectador espectador) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM ESPECTADOR WHERE id_espectador=?",
                new Object[] { espectador.getIdEspectador()});
    }
    
}
