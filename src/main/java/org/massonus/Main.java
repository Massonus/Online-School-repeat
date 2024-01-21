package org.massonus;

import org.massonus.entity.Course;
import org.massonus.entity.Person;
import org.massonus.repo.CourseRepo;
import org.massonus.service.CourseService;
import org.massonus.service.ManyUtils;
import org.massonus.view.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        final AdditionalMaterialView materialsView = context.getBean(AdditionalMaterialView.class);
        final HomeworkView homeworkView = context.getBean(HomeworkView.class);
        final LectureView lectureView = context.getBean(LectureView.class);
        final PersonView personView = context.getBean(PersonView.class);
        final CourseService courseService = context.getBean(CourseService.class);
        final CourseRepo courseRepo = context.getBean(CourseRepo.class);
        final LogView logView = context.getBean(LogView.class);

        final CourseView courseView = new CourseView(courseService, courseRepo, materialsView, homeworkView, lectureView, personView, logView);

        ManyUtils manyUtils = new ManyUtils();

        Person person = new Person();
        Person student1 = new Person();
        Person student2 = new Person();

        Course course = new Course();
        Course course1 = new Course();
        Course course2 = new Course();

        manyUtils.saveCourse(course);
        manyUtils.saveCourse(course1);
        manyUtils.saveCourse(course2);

        person.setFirstName("One");
        person.getCourses().add(course);
        person.getCourses().add(course1);

        student1.setFirstName("Two");
        student1.getCourses().add(course1);
        student1.getCourses().add(course2);

        student2.setFirstName("T");
        student2.getCourses().add(course2);

        manyUtils.saveStudent(person);
        manyUtils.saveStudent(student1);
        manyUtils.saveStudent(student2);

        List<Course> courses = courseView.firstCreate();
        courseView.mainMenu(courses);

    }
}
