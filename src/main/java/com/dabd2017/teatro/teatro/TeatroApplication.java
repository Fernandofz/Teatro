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
public class TeatroApplication{

	public static void main(String[] args) {
		SpringApplication.run(TeatroApplication.class, args);
	}


}
