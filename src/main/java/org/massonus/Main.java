package org.massonus;

import org.massonus.entity.Course;
import org.massonus.utils.CourseController;
import org.massonus.view.AdditionalMaterialsView;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        AdditionalMaterialsView materialsView = context.getBean("materialsView", AdditionalMaterialsView.class);

        CourseController courseController = new CourseController(materialsView);
        List<Course> courses = courseController.firstCreate();
        courseController.mainMenu(courses);
    }
}