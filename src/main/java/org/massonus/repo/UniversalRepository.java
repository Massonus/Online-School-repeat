package org.massonus.repo;

import lombok.SneakyThrows;
import org.massonus.log.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;
import java.util.Scanner;

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

    default int lengthMasByUser() {
        System.out.println("Enter how many elements do you want");
        Scanner scanner1 = new Scanner(System.in);

        return scanner1.nextInt();
    }

    default int lengthMasAuto() {
        Random random = new Random();
        return random.nextInt(1, 30);
    }
}
