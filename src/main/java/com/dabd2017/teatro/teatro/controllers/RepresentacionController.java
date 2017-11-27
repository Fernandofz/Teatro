package com.dabd2017.teatro.teatro.controllers;

import com.dabd2017.teatro.teatro.domain.dao.EntradaDao;
import com.dabd2017.teatro.teatro.domain.dao.EntradaDaoImp;
import com.dabd2017.teatro.teatro.domain.dao.EspectadorDao;
import com.dabd2017.teatro.teatro.domain.dao.EspectadorDaoImp;
import com.dabd2017.teatro.teatro.domain.dao.RepresentacionDao;
import com.dabd2017.teatro.teatro.domain.dao.RepresentacionDaoImp;
import com.dabd2017.teatro.teatro.domain.entity.Entrada;
import com.dabd2017.teatro.teatro.domain.entity.Espectador;
import com.dabd2017.teatro.teatro.domain.entity.Representacion;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Fernando Fernandez
 * @date 20/11/2017
 */
@RestController
public class RepresentacionController {

    private JdbcTemplate driver = DriverDB.getDriver();

    RepresentacionDao representacionDao = new RepresentacionDaoImp();

    EspectadorDao espectadorDao = new EspectadorDaoImp();

    EntradaDao entradaDao = new EntradaDaoImp();

    @GetMapping("/representaciones")
    public ResponseEntity<List<Representacion>> obtenerRepresentaciones(){
        List<Representacion> representacions = representacionDao.obtenerTodosActuales();
        return ResponseEntity.ok(representacions);
    }

    @GetMapping("/espectaculos")
    public  List<Map<String, Object>> obtenerEspectaculos(){
        String query  = "select r.id_representacion, o.titulo, o.genero, o.descripcion,o.image,r.fecha_obra, c.nombre from Representacion r inner join ObraTeatro o on r.id_obra_teatro = o.id_obra_teatro inner join Compañia c on r.id_compañia = c.id_compañia where r.fecha_obra >= now()";
        return driver.queryForList(query);
    }

    @PostMapping("/representar")
    public ResponseEntity<Representacion> representar(@RequestBody Representacion representacion){
        representacion = representacionDao.insertar(representacion);
        return ResponseEntity.ok(representacion);
    }
    @PostMapping("/espectador")
    public ResponseEntity<Espectador> espectador(@RequestBody Espectador espectador){
        espectadorDao.insertar(espectador);
        return ResponseEntity.ok(espectador);
    }

    @PostMapping("/butacas/{num}/{cant}")
    public void butacas(@PathVariable int num,@PathVariable int cant){
        for (int x=1;x<=cant; x++){
            Entrada entrada = new Entrada();
            entrada.setIdRepresentacion(num);
            entrada.setButaca(x);
            entrada.setOcupado(false);
            entradaDao.insertar(entrada);
        }

    }
}
