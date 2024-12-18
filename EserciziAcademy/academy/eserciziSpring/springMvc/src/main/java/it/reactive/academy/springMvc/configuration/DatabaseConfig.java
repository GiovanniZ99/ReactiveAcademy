package it.reactive.academy.springMvc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Scope(scopeName = "prototype")
public class DatabaseConfig {
    private final Connection con;

    public DatabaseConfig(@Value("${spring.datasource.url}") String url,
                          @Value("${spring.datasource.username}") String username,
                          @Value("${spring.datasource.password}") String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getCon() {
        return con;
    }

    public void closeCon() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
