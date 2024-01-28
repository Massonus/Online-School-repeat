package org.massonus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

        final Controller controller = context.getBean(Controller.class);
        controller.firstInitialize();
        controller.mainMenu();

    }
}
