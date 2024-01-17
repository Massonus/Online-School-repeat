package org.massonus;

import org.massonus.entity.Course;
import org.massonus.utils.CourseController;
import org.massonus.view.AdditionalMaterialsView;
import org.massonus.view.HomeworkView;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        final AdditionalMaterialsView materialsView = context.getBean("materialsView", AdditionalMaterialsView.class);
        final HomeworkView homeworkView = context.getBean("homeworkView", HomeworkView.class);

        CourseController courseController = new CourseController(materialsView, homeworkView);
        List<Course> courses = courseController.firstCreate();
        courseController.mainMenu(courses);
    }
}