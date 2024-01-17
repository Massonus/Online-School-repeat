package org.massonus;

import org.massonus.entity.Course;
import org.massonus.utils.CourseController;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CourseController courseController = new CourseController();
        List<Course> courses = courseController.firstCreate();
        courseController.mainMenu(courses);
    }
}