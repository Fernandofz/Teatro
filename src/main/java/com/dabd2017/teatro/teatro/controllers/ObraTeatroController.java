package com.dabd2017.teatro.teatro.controllers;

import com.dabd2017.teatro.teatro.domain.dao.ObraTeatroDao;
import com.dabd2017.teatro.teatro.domain.dao.ObraTeatroDaoImp;
import com.dabd2017.teatro.teatro.domain.entity.ObraTeatro;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Fer on 18/11/2017.
 */
@RestController
public class ObraTeatroController {

    private JdbcTemplate driver = DriverDB.getDriver();

    private ObraTeatroDao obraTeatroDao = new ObraTeatroDaoImp();

    @PostMapping("/obrasteatro")
    public ResponseEntity<ObraTeatro> crearObra(@RequestBody ObraTeatro obra){
        obraTeatroDao.insertar(obra);
        return ResponseEntity.ok().body(obra);
    }

    @GetMapping("/obrasteatro")
    public List<Map<String, Object>> obtenerObras(@RequestParam String nombre){
        String query;
        if(nombre==""){
          query  = "select o.id_obra_teatro,o.es_tercera,o.titulo,o.año as anio,o.descripcion,o.genero,o.image,p.nombre,p.apellido from obrateatro o inner join persona p on p.dni = o.dni_autor";
        }
        else {
            query  = "select o.id_obra_teatro,o.es_tercera,o.titulo,o.año as anio,o.descripcion,o.genero,o.image,p.nombre,p.apellido from obrateatro o inner join persona p on p.dni = o.dni_autor where o.titulo like '%"+ nombre +"%'";
        }
         return driver.queryForList(query);
    }

    @GetMapping("/verificarobrasteatro")
    public List<Map<String, Object>> verificarLocalmente(){
        String query="select distinct id_obra_teatro from representacion where id_compañia = 1";
        return driver.queryForList(query);
    }



}
