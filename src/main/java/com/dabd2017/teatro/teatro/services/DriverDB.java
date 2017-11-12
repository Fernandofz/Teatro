package com.dabd2017.teatro.teatro.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

/**
 * @author Fernando Fernandez
 * @date 05/11/2017
 */
public class DriverDB {
    private static DriverDB driverDB = new DriverDB();
    private static JdbcTemplate jdbcTemplate=null;
    private static DriverManagerDataSource driverManagerDataSource;

    public static JdbcTemplate getDriver(){
        if (jdbcTemplate==null){
            driverDB.startConnection();
        }
        return jdbcTemplate;
    }

    public void startConnection(){
        driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mariadb://localhost:3306/teatro");
        driverManagerDataSource.setUsername("tasker");
        driverManagerDataSource.setPassword("admin");
        jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
    }

    public static void stopConnection() throws SQLException {
        if (jdbcTemplate!=null){
            driverManagerDataSource.getConnection().close();
            jdbcTemplate = null;
        }
    }
}
