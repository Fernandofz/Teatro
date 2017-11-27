package com.dabd2017.teatro.teatro.controllers;

import com.dabd2017.teatro.teatro.domain.dao.EntradaDao;
import com.dabd2017.teatro.teatro.domain.dao.EntradaDaoImp;
import com.dabd2017.teatro.teatro.domain.entity.Entrada;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Fernando Fernandez
 * @date 21/11/2017
 */
@RestController
public class EntradaController {

    private EntradaDao entradaDao = new EntradaDaoImp();

    private JdbcTemplate driver = DriverDB.getDriver();

    @GetMapping("/entradas/{rep}")
    public List<Map<String, Object>> obtenerEntradas(@PathVariable int rep, @RequestParam String nombre){


        String query;
        if(nombre==""){
            query  = "select id_representacion,butaca,esta_ocupado from entrada where id_representacion = ?";
        }
        else if("no".equals(nombre)) {
            query  = "select id_representacion,butaca,esta_ocupado from entrada where id_representacion = ? and esta_ocupado = false";
        } else {
            query  = "select id_representacion,butaca,esta_ocupado from entrada where id_representacion = ? and butaca like '"+ nombre +"%'";
        }

        return driver.queryForList(query,new Object[]{rep});
    }

    @PutMapping("/abonarentrada")
    public ResponseEntity<Entrada> abonarEntrada(@RequestBody Entrada entrada){
        driver.update("UPDATE ENTRADA SET fecha = ?,id_espectador = ?,dni = ?,numero_abono = ?,esta_ocupado = ? WHERE id_representacion=? AND butaca=?",
                new Object[] {new Date(), entrada.getEspectador(),entrada.getDni(),entrada.getNumeroAbono(),true,entrada.getIdRepresentacion(),entrada.getButaca()});
        int cantusos = 0;

        String totalVenta="select cantidad_usos from abono where dni = "+ entrada.getDni() +" and numero = "+entrada.getNumeroAbono();
        SqlRowSet rs = driver.queryForRowSet(totalVenta);

        if (rs.next()){
            cantusos =  rs.getInt("cantidad_usos");
        }
        cantusos = cantusos - 1;
        driver.update("UPDATE ABONO SET cantidad_usos = ? WHERE dni=? AND numero=?",
                new Object[] {cantusos,entrada.getDni(),entrada.getNumeroAbono()});

        return ResponseEntity.ok().body(entrada);
    }

    @PutMapping("/ventaentrada")
    public ResponseEntity<Entrada> venderEntrada(@RequestBody Entrada entrada){
        driver.update("UPDATE ENTRADA SET fecha = ?,id_espectador = ?,dni_individual = ?,esta_ocupado = ? WHERE id_representacion=? AND butaca=?",
                new Object[] {new Date(), entrada.getEspectador(),entrada.getDniIndividual(),true,entrada.getIdRepresentacion(),entrada.getButaca()});
        return ResponseEntity.ok().body(entrada);
    }

    @GetMapping("/espectadores/{id}")
    public List<Map<String, Object>> obtenerEspectadores(@PathVariable int id){
        String query  = "select * from espectador where id_representacion = ?";
        return driver.queryForList(query,new Object[]{id});
    }


}
