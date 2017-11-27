package com.dabd2017.teatro.teatro.domain.dao;

import com.dabd2017.teatro.teatro.domain.entity.Entrada;
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
public class EntradaDaoImp implements EntradaDao {

   @Override
    public Entrada insertar(Entrada entrada){
        JdbcTemplate test = DriverDB.getDriver();
        test.update("INSERT INTO ENTRADA (id_representacion,butaca,fecha,id_espectador,dni,numero_abono,dni_individual) VALUES (?,?,?,?,?,?,?)",
                new Object[] { entrada.getIdRepresentacion(),entrada.getButaca(), entrada.getFecha(),entrada.getEspectador(),entrada.getDni(),entrada.getNumeroAbono(),entrada.getDniIndividual()});
        return entrada;
    }

    @Override
    public List<Entrada> obtenerTodos() {
        JdbcTemplate test = DriverDB.getDriver();
        return test.query( "select * from Entrada", new PersonaMapper());
    }

    private static final class PersonaMapper implements RowMapper {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Entrada entrada = new Entrada();
            entrada.setIdRepresentacion(rs.getInt("id_representacion"));
            entrada.setButaca(rs.getInt("butaca"));
            entrada.setFecha(rs.getDate("fecha"));
            entrada.setEspectador(rs.getInt("id_espectador"));
            entrada.setDni(rs.getInt("dni"));
            entrada.setNumeroAbono(rs.getInt("numero_abono"));
            entrada.setDniIndividual(rs.getInt("dni_individual"));
            return entrada;
        }
    }

    @Override
    public Entrada obtenerPorId(int id) {
        JdbcTemplate test = DriverDB.getDriver();
        SqlRowSet rs = test.queryForRowSet("select * from Entrada where id_entrada = ?", new Object[]{id});
        if (rs.next()){
            Entrada entrada = new Entrada();
            entrada.setIdRepresentacion(rs.getInt("id_representacion"));
            entrada.setButaca(rs.getInt("butaca"));
            entrada.setFecha(rs.getDate("fecha"));
            entrada.setEspectador(rs.getInt("id_espectador"));
            entrada.setDni(rs.getInt("dni"));
            entrada.setNumeroAbono(rs.getInt("numero_abono"));
            entrada.setDniIndividual(rs.getInt("dni_individual"));
            return entrada;
        }else {
            return null;
        }
    }

    @Override
    public Entrada actualizar(Entrada entrada) {

            JdbcTemplate test = DriverDB.getDriver();
            test.update("UPDATE ENTRADA SET fecha=?,id_espectador=?,dni=?,numero_abono=?,esta_ocupado=?,dni_individual=? WHERE id_representacion=? AND butaca=?",
                    new Object[] {entrada.getFecha(), entrada.getEspectador(),entrada.getDni(),entrada.getNumeroAbono(),entrada.getOcupado(),entrada.getDniIndividual(),entrada.getIdRepresentacion(),entrada.getButaca()});
            return entrada;
    }

    @Override
    public void borrar(Entrada entrada) {
        JdbcTemplate test = DriverDB.getDriver();
        test.update("DELETE FROM ENTRADA WHERE id_entrada=?",
                new Object[] { entrada.getIdRepresentacion()});
    }
}
