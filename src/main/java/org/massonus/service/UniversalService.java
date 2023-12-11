package org.massonus.service;

import org.massonus.entity.SuperSchool;
import org.massonus.log.Logger;

import java.util.List;
import java.util.Scanner;

public interface UniversalService<T extends SuperSchool> {

    Logger logger = new Logger("UniversalRepository");

    default String choice() {
        System.out.println("1. To create elements by yourself");
        System.out.println("2. To create elements automatically");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("Incorrect symbol");
            return choice();
        }
        return choice;
    }

    default int choiceIndex() {
        System.out.println("Enter an index");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    default int choiceId() {
        System.out.println("Enter id of Element");
        int id = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            id = scanner.nextInt();
        } catch (Exception e) {
            logger.error("mismatch " + e);
        }
        return id;
    }

    default boolean removeById(List<T> list, int id) {
        if (list == null) {
            System.out.println("Please create the List");
            logger.warning("array is empty");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                T remove = list.remove(i);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    default T getById(List<T> list, int id) {
        if (list == null) {
            System.out.println("Please create an Array");
            return null;
        }

        for (T element : list) {
            if (id == element.getId()) {
                return element;
            }
        }
        return null;
    }

    default boolean printAll(List<T> eList) {
        if (eList == null) {
            System.out.println("Please create an Array");
            logger.warning("array is empty");
            return false;
        }
        int i = 0;
        for (T element : eList) {
            System.out.println("\n index: " + i + " " + element + "\n");
            i++;
        }
        logger.info("courses printed");
        return true;
    }
}
