package org.massonus;

import org.massonus.entity.Course;
import org.massonus.repo.CourseRepo;
import org.massonus.service.CourseService;
import org.massonus.view.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        final AdditionalMaterialsView materialsView = context.getBean("materialsView", AdditionalMaterialsView.class);
        final HomeworkView homeworkView = context.getBean("homeworkView", HomeworkView.class);
        final LectureView lectureView = context.getBean("lectureView", LectureView.class);
        final PersonView personView = context.getBean("personView", PersonView.class);
        final CourseService courseService = context.getBean("courseService", CourseService.class);
        final CourseRepo courseRepo = context.getBean("courseRepo", CourseRepo.class);

        final CourseView courseView = new CourseView(courseService, courseRepo, materialsView, homeworkView, lectureView, personView);
        List<Course> courses = courseView.firstCreate();
        courseView.mainMenu(courses);
    }
}