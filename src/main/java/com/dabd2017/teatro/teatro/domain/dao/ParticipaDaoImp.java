package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Participa;
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
public class ParticipaDaoImp implements ParticipaDao {

    @Override
    public Participa insertar(Participa participa){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO PARTICIPA (id_participa,dni,id_obra_teatro,id_representacion) VALUES(?,?,?,?)",
                new Object[] { participa.getIdParticipa(),participa.getDni(), participa.getObraTeatro(), participa.getRepresentacion() });
        return participa;
    }

    @Override
    public List<Participa> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Participa", new ParticipaMapper());
    }

    private static final class ParticipaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Participa participa = new Participa();
            participa.setDni(rs.getInt("dni"));
            participa.setIdParticipa(rs.getInt("id_participa"));
            participa.setObraTeatro(rs.getInt("id_obra_teatro"));
            participa.setRepresentacion(rs.getInt("id_representacion"));

            return participa;
        }
    }

    @Override
    public Participa obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Participa where id = ?", new Object[]{id});
        if (rs.next()){
            Participa participa = new Participa();
            participa.setDni(rs.getInt("dni"));
            participa.setIdParticipa(rs.getInt("id_participa"));
            participa.setObraTeatro(rs.getInt("id_obra_teatro"));
            participa.setRepresentacion(rs.getInt("id_representacion"));

            return participa;
        }else {
            return null;
        }
    }


    @Override
    public void borrar(Participa participa) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM Participa WHERE id_participa=?",
                new Object[] { participa.getIdParticipa()});
    }

}
