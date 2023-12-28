package org.massonus.repo;

import lombok.SneakyThrows;
import org.massonus.log.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public interface UniversalRepository {

    Logger logger = new Logger("UniversalRepository");

    @SneakyThrows
    default Connection createCon() {
        final String url = "jdbc:postgresql://localhost:5432/Online School";
        final String username = "postgres";
        final String password = "root";
        Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
