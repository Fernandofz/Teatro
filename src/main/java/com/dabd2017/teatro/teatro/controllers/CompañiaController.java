package com.dabd2017.teatro.teatro.controllers;

import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Fernando Fernandez
 * @date 22/11/2017
 */
@RestController
public class CompañiaController {

    JdbcTemplate driver = DriverDB.getDriver();

    @GetMapping("/compañias")
    public List<Map<String, Object>> obtenerCompañia(@RequestParam String nombre){
        String query;
        if(nombre==""){
            query  = "select * from compañia";
        }
        else {
            query  = "select * from compañia where nombre like '%"+nombre+"%'";

        }
        return driver.queryForList(query);
    }

    @GetMapping("/organismos")
    public List<Map<String, Object>> obtenerOrganismo(@RequestParam String nombre){
        String query;
        if(nombre==""){
            query  = "select * from organismo";
        }
        else {
            query  = "select * from organismo where nombre like '%"+nombre+"%'";

        }
        return driver.queryForList(query);
    }

}
