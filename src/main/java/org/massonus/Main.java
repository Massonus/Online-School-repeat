package org.massonus;

import org.massonus.entity.Course;
import org.massonus.view.CourseView;
import org.massonus.view.AdditionalMaterialsView;
import org.massonus.view.HomeworkView;
import org.massonus.view.LectureView;
import org.massonus.view.PersonView;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        final AdditionalMaterialsView materialsView = context.getBean("materialsView", AdditionalMaterialsView.class);
        final HomeworkView homeworkView = context.getBean("homeworkView", HomeworkView.class);
        final LectureView lectureView = context.getBean("lectureView", LectureView.class);
        final PersonView personView = context.getBean("personView", PersonView.class);

        CourseView courseView = new CourseView(materialsView, homeworkView, lectureView, personView);
        List<Course> courses = courseView.firstCreate();
        courseView.mainMenu(courses);
    }
}