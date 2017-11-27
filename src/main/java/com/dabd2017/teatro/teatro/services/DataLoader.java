package com.dabd2017.teatro.teatro.services;

import com.dabd2017.teatro.teatro.domain.dao.*;
import com.dabd2017.teatro.teatro.domain.entity.Compañia;
import com.dabd2017.teatro.teatro.domain.entity.Entrada;
import com.dabd2017.teatro.teatro.domain.entity.Espectador;
import com.dabd2017.teatro.teatro.domain.entity.ObraTeatro;
import com.dabd2017.teatro.teatro.domain.entity.Organismo;
import com.dabd2017.teatro.teatro.domain.entity.Persona;
import com.dabd2017.teatro.teatro.domain.entity.Representacion;
import com.dabd2017.teatro.teatro.domain.entity.Subvencion;
import com.dabd2017.teatro.teatro.domain.entity.TipoAbono;
import com.dabd2017.teatro.teatro.domain.entity.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Fer on 18/11/2017.
 */
//@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PersonaDao personaDao;

    private ObraTeatroDao obraTeatroDao = new ObraTeatroDaoImp();

    private CompañiaDao compañiaDao = new CompañiaDaoImp();

    private RepresentacionDao representacionDao = new RepresentacionDaoImp();

    private VentaDao ventaDao = new VentaDaoImp();

    private EntradaDao entradaDao = new EntradaDaoImp();

    private TipoAbonoDao tipoAbonoDao = new TipoAbonoDaoImp();

    private EspectadorDao espectadorDao = new EspectadorDaoImp();

    private OrganismoDao organismoDao = new OrganismoDaoImp();

    private SubvencioDao subvencioDao = new SubvencionDaoImp();

    private SimpleDateFormat formater = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

    @Override
    public void run(String... strings) throws Exception {
        Random rnd = new Random();

        //-------------------------------------- Autores

        Persona persona1 = new Persona();
        persona1.setDni(1);
        persona1.setNombre("Fernando");
        persona1.setApellido("De Rojas");
        persona1.setDireccion("");
        persona1.setTelefono("");
        personaDao.insertar(persona1);

        Persona persona2 = new Persona();
        persona2.setDni(2);
        persona2.setNombre("William");
        persona2.setApellido("Shakespeare");
        persona2.setDireccion("");
        persona2.setTelefono("");
        personaDao.insertar(persona2);

        //----------------------------------------- Obras de teatros

        ObraTeatro obraTeatro = new ObraTeatro();
        obraTeatro.setAño(1597);
        obraTeatro.setGenero("Romance");
        obraTeatro.setDescripcion("Cuenta la historia de dos jóvenes enamorados que, a pesar de la oposición de sus familias, rivales entre sí, deciden casarse de forma clandestina y vivir juntos");
        obraTeatro.setImage("../images/romeo-and-juliet.jpg");
        obraTeatro.setTitulo("Romeo Y Julieta");
        obraTeatro.setDniAutor(2);
        obraTeatro.setEsTercera(true);
        obraTeatro.setNacionalidad("Inglesa");

        obraTeatroDao.insertar(obraTeatro);

        ObraTeatro obraTeatro1 = new ObraTeatro();
        obraTeatro1.setAño(1792);
        obraTeatro1.setGenero("Comedia");
        obraTeatro1.setDescripcion("La obra comienza cuando Calisto ve casualmente a Melibea en el huerto de su casa, donde ha entrado a buscar un halcón suyo. Pidiéndole su amor, esta lo rechaza, pero ya es tarde, ha caído violentamente enamorado de Melibea.");
        obraTeatro1.setImage("../images/Lacelestina.jpg");
        obraTeatro1.setTitulo("La celestina");
        obraTeatro1.setDniAutor(1);
        obraTeatro1.setEsTercera(true);
        obraTeatro1.setNacionalidad("Española");

        obraTeatroDao.insertar(obraTeatro1);

        ObraTeatro obraTeatro2 = new ObraTeatro();
        obraTeatro2.setAño(1601);
        obraTeatro2.setGenero("Drama");
        obraTeatro2.setDescripcion("Desde principios del siglo XVII, la obra alcanzó altos niveles de fama por la aparición fantasmagórica de un muerto y por la vívida dramatización de la melancolía y la locura, dando lugar a una procesión de cortesanos desquiciados a la manera del drama de las épocas jacobina y carolina de la literatura inglesa.");
        obraTeatro2.setImage("../images/hamlet.jpg");
        obraTeatro2.setTitulo("HAMLET");
        obraTeatro2.setEsTercera(true);
        obraTeatro2.setDniAutor(2);
        obraTeatro2.setNacionalidad("indo-europeo");

        obraTeatroDao.insertar(obraTeatro2);

        //--------------------------------------------- Personas

        String [] nombres = {"Juan","Carlos","Martita"};
        for (String nombre: nombres){

            Persona persona = new Persona();
            persona.setDni(rnd.nextInt(40000000));
            persona.setNombre(nombre);
            persona.setApellido("Perez");
            persona.setDireccion("Alguna Calle " + rnd.nextInt(2000));
            persona.setTelefono((rnd.nextInt(99999999))+"");
            personaDao.insertar(persona);

        }

        //------------------------------------------ Tipos Abono

        TipoAbono tipoAbono = new TipoAbono();
        tipoAbono.setNombre("Abono 3");
        tipoAbono.setIdTipoAbono(1);
        tipoAbono.setPrecio(250F);
        tipoAbono.setCantidadUsos(3);

        TipoAbono tipoAbono2 = new TipoAbono();
        tipoAbono2.setNombre("Abono 8");
        tipoAbono2.setIdTipoAbono(2);
        tipoAbono2.setPrecio(500F);
        tipoAbono2.setCantidadUsos(8);

        TipoAbono tipoAbono3 = new TipoAbono();
        tipoAbono3.setNombre("Abono Unlimit");
        tipoAbono3.setIdTipoAbono(3);
        tipoAbono3.setPrecio(1500F);
        tipoAbono3.setCantidadUsos(Integer.MAX_VALUE);

        tipoAbonoDao.insertar(tipoAbono);
        tipoAbonoDao.insertar(tipoAbono2);
        tipoAbonoDao.insertar(tipoAbono3);

        //--------------------------------------- Organismo

        Organismo organismo = new Organismo();
        organismo.setNombre("Casa de la Cultura");
        organismo.setDireccion("Marcelo T. Alvear 50");
        organismo.setTelefono("4444859");
        organismo.setDescripcion("La Casa de la Cultura fortalece la integracion de las corrientes de pensamiento.");

        organismoDao.insertar(organismo);


        //--------------------------------------- Subvencion

        Subvencion subvencion = new Subvencion();
        subvencion.setAporte(257f);
        subvencion.setFecha(new Date());
        subvencion.setOrganismo(1);

        subvencion.setObraTeatro(1);
        subvencioDao.insertar(subvencion);
        subvencion.setObraTeatro(2);
        subvencioDao.insertar(subvencion);
        subvencion.setObraTeatro(3);
        subvencioDao.insertar(subvencion);

        //--------------------------------------- Compañia

        Compañia compañia = new Compañia();
        compañia.setNombre("Leitmotiv");
        compañia.setPrestigio("Bueno");
        compañia.setDireccion("Centro");
        compañia.setEsTercera(false);

        compañiaDao.insertar(compañia);

        Compañia compañia1 = new Compañia();
        compañia1.setNombre("Gala");
        compañia1.setPrestigio("Bueno");
        compañia1.setDireccion("Centro");
        compañia1.setEsTercera(true);

        compañiaDao.insertar(compañia1);

        //----------------------------------------- Representación

        String anuncio1 = "22-10-2017 10:20:00";
        String fecha1 = "10-11-2017 10:20:00";
        Representacion representacion1 = new Representacion();
        representacion1.setIdCompañia(1);
        representacion1.setIdObraTeatro(1);
        representacion1.setPrecioCompra(0F);
        representacion1.setCostoMantenimiento(2700F);
        representacion1.setCostoPreparacion(8650F);
        representacion1.setFechaAnuncio(formater.parse(anuncio1));
        representacion1.setFechaObra(formater.parse(fecha1));

        representacion1 = representacionDao.insertar(representacion1);

        String anuncio2 = "11-11-2017 10:20:00";
        String fecha2 = "20-12-2017 10:20:00";
        Representacion representacion2 = new Representacion();
        representacion2.setIdCompañia(1);
        representacion2.setIdObraTeatro(1);
        representacion2.setPrecioCompra(0F);
        representacion2.setCostoMantenimiento(2700F);
        representacion2.setCostoPreparacion(8650F);
        representacion2.setFechaAnuncio(formater.parse(anuncio2));
        representacion2.setFechaObra(formater.parse(fecha2));

        representacion2 = representacionDao.insertar(representacion2);

        //------------------------------------------ Espectadores

        Espectador espectador = new Espectador();
        espectador.setIdRepresentacion(1);
        espectador.setTipo("Adulto");
        espectador.setPrecio(100F);

        Espectador espectador1 = new Espectador();
        espectador1.setIdRepresentacion(1);
        espectador1.setTipo("Niños");
        espectador1.setPrecio(100F);

        espectadorDao.insertar(espectador);
        espectadorDao.insertar(espectador1);

        espectador.setIdRepresentacion(2);
        espectador.setPrecio(120F);

        espectador1.setIdRepresentacion(2);
        espectador1.setPrecio(70F);

        espectadorDao.insertar(espectador);
        espectadorDao.insertar(espectador1);

        //------------------------------------------ Entradas

        for (int i=1;i<500;i++){
            Entrada entrada = new Entrada();
            entrada.setIdRepresentacion(representacion1.getIdRepresentacion());
            entrada.setButaca(i);
            entradaDao.insertar(entrada);
        }

        for (int i=1;i<500;i++){
            Entrada entrada = new Entrada();
            entrada.setIdRepresentacion(representacion2.getIdRepresentacion());
            entrada.setButaca(i);
            entradaDao.insertar(entrada);
        }

        //------------------------------------------ Ventas


        String fechav1 = "11-11-2017 10:20:00";
        Venta venta1 = new Venta();
        venta1.setFecha(formater.parse(fechav1));
        venta1.setCompañia(2);
        venta1.setObraTeatro(1);
        venta1.setPrecio(4000);

        ventaDao.insertar(venta1);


        String fechav2 = "11-11-2017 10:20:00";
        Venta venta2 = new Venta();
        venta2.setFecha(formater.parse(fechav2));
        venta2.setCompañia(2);
        venta2.setObraTeatro(2);
        venta2.setPrecio(3400);

        ventaDao.insertar(venta1);

        //--------------------------------------------


    }

}
