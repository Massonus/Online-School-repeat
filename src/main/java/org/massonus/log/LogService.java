package org.massonus.log;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class LogService {
    final Path path = Path.of("src/main/java/org/massonus/log/logs.txt");

    public List<String> readLogs() {
        List<String> strings = new ArrayList<>();
        try {
            strings = Files.readAllLines(path);
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
        return strings;
    }

    public void writeLogs(Log log) {
        String stringLog = log.toString();
        try {
            if (!Files.exists(path)) {
                final Path done = Files.createFile(path);
                System.out.println("File created: " + done);
            }
            Files.write(path, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
            Files.write(path, stringLog.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
    }

    public void deleteLineOfLog() {
        List<String> currentLogs = readLogs();
        int iter = 0;
        for (String currentLog : currentLogs) {
            System.out.println("Index: " + iter + "; " + currentLog);
            iter++;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of line to delete");
        int index = scanner.nextInt();
        currentLogs.remove(index);
        try {
            Files.write(path, currentLogs);
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
    }

    public void printCurrentInfoLogs(List<String> currentLogs) {
        currentLogs.stream()
                .filter(l -> l.startsWith("INFO", 23))
                .forEach(System.out::println);
    }
}