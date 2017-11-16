package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.ObraTeatro;
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
public class ObraTeatroDaoImp implements ObraTeatroDao {

    @Override
    public ObraTeatro insertar(ObraTeatro obraTeatro){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO ObraTeatro (titulo,genero,año,image) VALUES(?,?,?,?)",
                new Object[] { obraTeatro.getTitulo(),obraTeatro.getGenero(), obraTeatro.getAño(), obraTeatro.getImage()});
        return obraTeatro;
    }

    @Override
    public List<ObraTeatro> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from ObraTeatro", new ObraTeatroMapper());
    }

    private static final class ObraTeatroMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            ObraTeatro obraTeatro = new ObraTeatro();
            obraTeatro.setIdObraTeatro(rs.getInt("id_obra_teatro"));
            obraTeatro.setTitulo(rs.getString("titulo"));
            obraTeatro.setGenero(rs.getString("genero"));
            obraTeatro.setAño(rs.getInt("año"));
            obraTeatro.setImage(rs.getString("image"));
            return obraTeatro;
        }
    }

    @Override
    public ObraTeatro obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from ObraTeatro where id_obra_teatro = ?", new Object[]{id});
        if (rs.next()){
            ObraTeatro obraTeatro = new ObraTeatro();
            obraTeatro.setIdObraTeatro(rs.getInt("id_obra_teatro"));
            obraTeatro.setTitulo(rs.getString("titulo"));
            obraTeatro.setGenero(rs.getString("genero"));
            obraTeatro.setAño(rs.getInt("año"));
            obraTeatro.setImage(rs.getString("image"));
            return obraTeatro;
        }else {
            return null;
        }
    }

    @Override
    public ObraTeatro actualizar(ObraTeatro obraTeatro) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("UPDATE ObraTeatro SET titulo=?,genero=?,año=?,image=? WHERE id_obra_teatro=?",
                new Object[] { obraTeatro.getTitulo(),obraTeatro.getGenero(), obraTeatro.getAño(), obraTeatro.getImage(), obraTeatro.getIdObraTeatro()});
        return obraTeatro;
    }

    @Override
    public void borrar(ObraTeatro obraTeatro) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM ObraTeatro WHERE id_obra_teatro=?",
                new Object[] { obraTeatro.getIdObraTeatro()});
    }
}
