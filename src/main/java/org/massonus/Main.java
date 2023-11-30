package org.massonus;

import org.massonus.utils.CourseController;

public class Main {
    public static void main(String[] args) {
        CourseController courseController = new CourseController();
        courseController.firstCreate();
        courseController.mainMenu();
    }
}