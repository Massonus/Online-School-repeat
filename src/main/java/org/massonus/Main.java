package org.massonus;

import org.massonus.config.JpaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        final ConsoleController consoleController = context.getBean(ConsoleController.class);
        consoleController.firstInitialize();
        consoleController.mainMenu();

    }
}
