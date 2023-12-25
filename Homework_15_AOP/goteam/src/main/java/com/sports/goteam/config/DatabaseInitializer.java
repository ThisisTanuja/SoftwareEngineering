package com.sports.goteam.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//Annotating this class as a Spring Component so it is detected during component scanning
@Component
public class DatabaseInitializer {

	//Autowiring the DataSource object which is configured to connect to the database
    @Autowired
    private DataSource dataSource;

    //Injecting the database URL, username, and password from the application properties into the databaseUrl, username, and password variable respectively
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    //The @PostConstruct annotation indicates that this method should be run after the bean's properties have been set
    @PostConstruct
    public void initialize() {
    	//Using a try-with-resources statement to ensure the Connection is closed automatically
        try (Connection connection = dataSource.getConnection()) {
            //Creating a statement object to execute SQL commands.
            Statement statement = connection.createStatement();

            //Extracting the database name from the provided URL by finding the last '/' and taking the substring from there
            String dbName = databaseUrl.substring(databaseUrl.lastIndexOf("/") + 1);

            //Preparing the SQL statement to create the database if it doesn't exist
            //Backticks are used because this syntax is specific to MySQL
            String sql = "CREATE DATABASE IF NOT EXISTS `" + dbName + "`";

            //Executing the SQL statement to create the database
            statement.executeUpdate(sql);
            
        } catch (SQLException e) {
            //If there's any SQL exception, wrapping it in a runtime exception and throw it
            throw new RuntimeException("Error creating database", e);
        }
    }
}
