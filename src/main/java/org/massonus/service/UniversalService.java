package org.massonus.service;

import org.massonus.log.Logger;

import java.util.List;

public interface UniversalService<T> {

    Logger logger = new Logger("UniversalRepository");

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
