package org.massonus.repo;

import org.massonus.log.Logger;

import java.util.Scanner;

public interface UniversalRepository {

    Logger logger = new Logger("UniversalRepository");

    default int lengthMas() {
        System.out.println("Enter how many elements do you want");
        Scanner scanner1 = new Scanner(System.in);

        return scanner1.nextInt();
    }
}
