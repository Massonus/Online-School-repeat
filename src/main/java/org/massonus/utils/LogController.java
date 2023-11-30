package org.massonus.utils;

import org.massonus.log.LogService;

import java.util.List;
import java.util.Scanner;

public class LogController {
    final LogService logService = new LogService();
    List<String> previousLogs = logService.readLogs();

    public void loggerMenu() {
        List<String> readLogs = logService.readLogs();

        while (true) {
            System.out.println("What do you want?");
            System.out.println("1. Print current logs");
            System.out.println("2. Print current logs (INFO only)");
            System.out.println("3. Print previous logs");
            System.out.println("4. To delete line");
            System.out.println("5. Read half of lines");
            System.out.println("0 To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    readLogs.forEach(System.out::println);
                    break;
                case "2":
                    readLogs.stream()
                            .filter(l -> l.startsWith("INFO", 24))
                            .forEach(System.out::println);

                    break;

                case "3":
                    previousLogs.forEach(System.out::println);
                    break;

                case "4":
                    logService.deleteLineOfLog();
                    break;

                case "5":
                    List<String> list = readLogs.subList(readLogs.size() / 2, readLogs.size());
                    list.forEach(System.out::println);
                    int size = list.size();
                    System.out.println(size);
                    break;
                case "0":
                    return;
            }
        }
    }
}
