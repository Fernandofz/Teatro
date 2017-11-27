package com.dabd2017.teatro.teatro.controllers;

import com.dabd2017.teatro.teatro.domain.entity.Compañia;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Fernando Fernandez
 * @date 22/11/2017
 */
@RestController
public class EstadisticaController {

    JdbcTemplate driver = DriverDB.getDriver();

    @GetMapping("/totalingresos")
    public ResponseEntity<Float> obtenerTotalDeIngresos(){
        float total =0;
        String totalVenta="Select   COALESCE(sum(precio),0) as totalventa from venta";
        SqlRowSet rs = driver.queryForRowSet(totalVenta);

        if (rs.next()){
            total += rs.getFloat("totalventa");
        }

        String totalSubvencion="Select  COALESCE(sum(aporte),0) as totalSubvencion from subvencion";
        rs = driver.queryForRowSet(totalSubvencion);
        if (rs.next()){
            total += rs.getFloat("totalSubvencion");
        }

        String totalVentaIndividuales="Select COALESCE(sum(precio),0) as totalVentaIndividuales from entrada e  inner join espectador es on e.id_espectador = es.id_espectador where e.numero_abono is null";
        rs = driver.queryForRowSet(totalVentaIndividuales);
        if (rs.next()){
            total += rs.getFloat("totalVentaIndividuales");
        }


        String totalEntradasAbonadas="Select  COALESCE(sum(ta.precio),0) as totalEntradasAbonadas from persona p inner join abono a on a.dni = p.dni inner join tipoabono ta on ta.id_tipo_abono = a.id_tipo_abono";
        rs = driver.queryForRowSet(totalEntradasAbonadas);
        if (rs.next()){
            total += rs.getFloat("totalEntradasAbonadas");
        }

        return ResponseEntity.ok(total);
    }


    @GetMapping("/ingresosporsubvenciones")
    public ResponseEntity<Float> obtenerTotalDeIngresosPorSubvenciones(){
        float total =0;
        String totalSubvencion="Select  COALESCE(sum(aporte),0) as totalSubvencion from subvencion";
        SqlRowSet rs = driver.queryForRowSet(totalSubvencion);
        if (rs.next()){
            total += rs.getFloat("totalSubvencion");
        }
        return ResponseEntity.ok(total);
    }

    @GetMapping("/ingresosporentrada")
    public ResponseEntity<Float> obtenerTotalDeIngresosPorEntradas(){
        float total =0;
        String totalVentaIndividuales="Select COALESCE(sum(precio),0) as totalVentaIndividuales from entrada e  inner join espectador es on e.id_espectador = es.id_espectador where e.numero_abono is null";
        SqlRowSet rs = driver.queryForRowSet(totalVentaIndividuales);
        if (rs.next()){
            total += rs.getFloat("totalVentaIndividuales");
        }

        String totalEntradasAbonadas="Select  COALESCE(sum(ta.precio),0) as totalEntradasAbonadas from persona p inner join abono a on a.dni = p.dni inner join tipoabono ta on ta.id_tipo_abono = a.id_tipo_abono";
        rs = driver.queryForRowSet(totalEntradasAbonadas);
        if (rs.next()){
            total += rs.getFloat("totalEntradasAbonadas");
        }
        return ResponseEntity.ok(total);
    }

    @GetMapping("/ingresosporventa")
    public ResponseEntity<Float> obtenerTotalDeIngresosPorVenta(){
        float total =0;
        String totalVenta="Select   COALESCE(sum(precio),0) as totalventa from venta";
        SqlRowSet rs = driver.queryForRowSet(totalVenta);

        if (rs.next()){
            total += rs.getFloat("totalventa");
        }
        return ResponseEntity.ok(total);
    }

    @GetMapping("/totalegresos")
    public ResponseEntity<Float> obtenerTotalDeEgresos(){
        float total =0;
        String totalEgreso="select COALESCE(sum(r.precio_compra + r.costo_mantenimiento + r.costo_preparacion),0) as totalegreso from representacion r";
        SqlRowSet rs = driver.queryForRowSet(totalEgreso);

        if (rs.next()){
            total += rs.getFloat("totalegreso");
        }

        return ResponseEntity.ok(total);
    }


    @GetMapping("/totalingresos/{idObra}")
    public ResponseEntity<Float> obtenerTotalDeIngresosPorObra(@PathVariable int idObra){
        float total =0;
        String totalVenta="select COALESCE(sum(precio),0) as totalventa from venta where id_obra_teatro = " + idObra;
        SqlRowSet rs = driver.queryForRowSet(totalVenta);

        if (rs.next()){
            total += rs.getFloat("totalventa");
        }

        String totalSubvencion="select COALESCE(sum(s.aporte),0) as totalSubvencion from obrateatro o inner join subvencion s on o.id_obra_teatro = s.id_obra_teatro where o.id_obra_teatro = "+idObra;
        rs = driver.queryForRowSet(totalSubvencion);
        if (rs.next()){
            total += rs.getFloat("totalSubvencion");
        }

        String totalVentaIndividuales="select COALESCE(sum(ep.precio),0) as totalVentaIndividuales from representacion r inner join entrada e on r.id_representacion = e.id_representacion inner join espectador ep on ep.id_espectador = e.id_espectador where r.id_obra_teatro = "+idObra;
        rs = driver.queryForRowSet(totalVentaIndividuales);
        if (rs.next()){
            total += rs.getFloat("totalVentaIndividuales");
        }


        String totalEntradasAbonadas="select COALESCE(sum(t.precio),0) as totalEntradasAbonadas from representacion r inner join entrada e on r.id_representacion = e.id_representacion inner join abono a on a.dni = e.dni and a.numero = e.numero_abono inner join tipoabono t on t.id_tipo_abono = a.id_tipo_abono where r.id_obra_teatro = "+idObra;
        rs = driver.queryForRowSet(totalEntradasAbonadas);
        if (rs.next()){
            total += rs.getFloat("totalEntradasAbonadas");
        }

        return ResponseEntity.ok(total);
    }


    @GetMapping("/totalegresos/{idObra}")
    public ResponseEntity<Float> obtenerTotalDeEgresoPorObra(@PathVariable int idObra){
        float total =0;
        String totalEgreso="select COALESCE(sum(r.precio_compra + r.costo_mantenimiento + r.costo_preparacion),0) as totalegreso from representacion r where r.id_obra_teatro = " +idObra;
        SqlRowSet rs = driver.queryForRowSet(totalEgreso);

        if (rs.next()){
            total += rs.getFloat("totalegreso");
        }

        return ResponseEntity.ok(total);
    }

}
