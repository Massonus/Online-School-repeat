package org.massonus.repo;

import org.massonus.log.Logger;

import java.util.Random;
import java.util.Scanner;

public interface UniversalRepository {

    Logger logger = new Logger("UniversalRepository");

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
