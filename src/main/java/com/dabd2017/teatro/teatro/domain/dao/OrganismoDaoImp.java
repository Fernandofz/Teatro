package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Organismo;
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
public class OrganismoDaoImp implements OrganismoDao {


    @Override
    public Organismo insertar(Organismo organismo){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO ORGANISMO (nombre,direccion,telefono,descripcion) VALUES(?,?,?,?)",
                new Object[] { organismo.getNombre(),organismo.getDireccion(), organismo.getTelefono(), organismo.getDescripcion()});
        return organismo;
    }

    @Override
    public List<Organismo> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Organismo", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organismo organismo = new Organismo();
            organismo.setIdOrganismo(rs.getInt("id_organismo"));
            organismo.setNombre(rs.getString("nombre"));
            organismo.setDireccion(rs.getString("direccion"));
            organismo.setTelefono(rs.getString("telefono"));
            organismo.setDescripcion(rs.getString("descripcion"));
            return organismo;
        }
    }

    @Override
    public Organismo obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Organismo where id = ?", new Object[]{id});
        if (rs.next()){
            Organismo organismo = new Organismo();
            organismo.setIdOrganismo(rs.getInt("id_organismo"));
            organismo.setNombre(rs.getString("nombre"));
            organismo.setDireccion(rs.getString("direccion"));
            organismo.setTelefono(rs.getString("telefono"));
            organismo.setDescripcion(rs.getString("descripcion"));
            return organismo;
        }else {
            return null;
        }
    }

    @Override
    public Organismo actualizar(Organismo organismo) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("UPDATE ORGANISMO SET nombre=?,direccion=?,telefono=?,descripcion=? WHERE id_organismo=?",
                new Object[] { organismo.getNombre(),organismo.getDireccion(), organismo.getTelefono(), organismo.getDescripcion(),organismo.getIdOrganismo()});
        return organismo;
    }

    @Override
    public void borrar(Organismo organismo) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM ORGANISMO WHERE id_organismo=?",
                new Object[] { organismo.getIdOrganismo()});
    }
}
