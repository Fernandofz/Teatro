package com.dabd2017.teatro.teatro;

import com.dabd2017.teatro.teatro.domain.dao.PersonaDao;
import com.dabd2017.teatro.teatro.domain.dao.PersonaDaoImp;
import com.dabd2017.teatro.teatro.domain.entity.Persona;
import com.dabd2017.teatro.teatro.services.DriverDB;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class TeatroApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TeatroApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		PersonaDao personaDao = new PersonaDaoImp();

		Persona persona = new Persona();
		persona.setDni(200);
		persona.setNombre("asd");
		persona.setApellido("asd");
		persona.setDireccion("qwe");
		persona.setTelefono("123");

		personaDao.insertar(persona);


		List<Persona> personas = personaDao.obtenerTodos();

		System.out.println(personas.size());
		System.out.println(personas.get(0).getDni());
	}
}
