package org.massonus;

import org.massonus.entity.Course;
import org.massonus.repo.CourseRepo;
import org.massonus.service.CourseService;
import org.massonus.view.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        final AdditionalMaterialsView materialsView = context.getBean(AdditionalMaterialsView.class);
        final HomeworkView homeworkView = context.getBean(HomeworkView.class);
        final LectureView lectureView = context.getBean(LectureView.class);
        final PersonView personView = context.getBean(PersonView.class);
        final CourseService courseService = context.getBean(CourseService.class);
        final CourseRepo courseRepo = context.getBean(CourseRepo.class);
        final LogView logView = context.getBean(LogView.class);

        final CourseView courseView = new CourseView(courseService, courseRepo, materialsView, homeworkView, lectureView, personView, logView);
        List<Course> courses = courseView.firstCreate();
        courseView.mainMenu(courses);
    }
}