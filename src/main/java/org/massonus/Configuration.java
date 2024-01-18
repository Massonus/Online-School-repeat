package org.massonus;

import org.massonus.entity.*;
import org.massonus.log.LogService;
import org.massonus.repo.*;
import org.massonus.service.*;
import org.massonus.view.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Scope("singleton")
    @Bean
    public AdditionalMaterial additionalMaterial() {
        return new AdditionalMaterial();
    }

    @Scope("singleton")
    @Bean
    public Course course() {
        return new Course();
    }

    @Scope("singleton")
    @Bean
    public Homework homework() {
        return new Homework();
    }

    @Scope("singleton")
    @Bean
    public Lecture lecture() {
        return new Lecture();
    }

    @Scope("singleton")
    @Bean
    public Person person() {
        return new Person();
    }

    @Scope("singleton")
    @Bean
    public AdditionalMaterialRepo materialRepo() {
        return new AdditionalMaterialRepo();
    }

    @Scope("singleton")
    @Bean
    public CourseRepo courseRepo() {
        return new CourseRepo(personRepo(), lectureRepo());
    }

    @Scope("singleton")
    @Bean
    public HomeworkRepo homeworkRepo() {
        return new HomeworkRepo();
    }

    @Scope("singleton")
    @Bean
    public PersonRepo personRepo() {
        return new PersonRepo();
    }

    @Scope("singleton")
    @Bean
    public LectureRepo lectureRepo() {
        return new LectureRepo(personRepo(), materialRepo(), homeworkRepo());
    }

    @Scope("singleton")
    @Bean
    public AdditionalMaterialService materialService() {
        return new AdditionalMaterialService(materialRepo());
    }

    @Scope("singleton")
    @Bean
    public CourseService courseService() {
        return new CourseService();
    }

    @Scope("singleton")
    @Bean
    public HomeworkService homeworkService() {
        return new HomeworkService(homeworkRepo());
    }

    @Scope("singleton")
    @Bean
    public PersonService personService() {
        return new PersonService(personRepo());
    }

    @Scope("singleton")
    @Bean
    public LectureService lectureService() {
        return new LectureService(lectureRepo(), materialService(), homeworkService(), personService());
    }

    @Scope("singleton")
    @Bean
    public LogService logService() {
        return new LogService();
    }

    @Scope("singleton")
    @Bean
    public AdditionalMaterialView materialsView() {
        return new AdditionalMaterialView(materialService(), lectureService(), courseService());
    }

    @Scope("singleton")
    @Bean
    public HomeworkView homeworkView() {
        return new HomeworkView(homeworkService(), lectureService(), courseService());
    }

    @Scope("singleton")
    @Bean
    public PersonView personView() {
        return new PersonView(personService(), courseService());
    }

    @Scope("singleton")
    @Bean
    public LectureView lectureView() {
        return new LectureView(lectureService(), courseService());
    }

    @Scope("singleton")
    @Bean
    public LogView logView() {
        return new LogView(logService());
    }

    @Scope("singleton")
    @Bean
    public CourseView courseView() {
        return new CourseView(courseService(), courseRepo(), materialsView(), homeworkView(), lectureView(), personView(), logView());
    }
}
