package com.dabd2017.teatro.teatro.config;

import com.dabd2017.teatro.teatro.domain.dao.PersonaDao;
import com.dabd2017.teatro.teatro.domain.dao.PersonaDaoImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fernando Fernandez
 * @date 12/11/2017
 */
@Configuration
public class SpringDaoConfig {

    @Bean
    public PersonaDao personaDao(){
        return new PersonaDaoImp();
    }

}
