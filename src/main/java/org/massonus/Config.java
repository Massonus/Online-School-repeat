package org.massonus;

import org.massonus.log.LogService;
import org.massonus.repo.*;
import org.massonus.service.*;
import org.massonus.view.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

    @Scope("singleton")
    @Bean
    public AdditionalMaterialRepo materialRepo() {
        return new AdditionalMaterialRepo();
    }

    @Scope("singleton")
    @Bean
    public CourseRepo courseRepo() {
        return new CourseRepo();
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
        return new LectureRepo();
    }

    @Scope("singleton")
    @Bean
    public AdditionalMaterialService materialService() {
        return new AdditionalMaterialService(materialRepo());
    }

    @Scope("singleton")
    @Bean
    public CourseService courseService() {
        return new CourseService(courseRepo(), personService(), lectureRepo());
    }

    @Scope("singleton")
    @Bean
    public HomeworkService homeworkService() {
        return new HomeworkService(homeworkRepo());
    }

    @Scope("singleton")
    @Bean
    public PersonService personService() {
        return new PersonService(personRepo(), lectureService());
    }

    @Scope("singleton")
    @Bean
    public LectureService lectureService() {
        return new LectureService(lectureRepo(), materialService(), materialRepo(), homeworkService(), homeworkRepo(), personRepo());
    }

    @Scope("singleton")
    @Bean
    public LogService logService() {
        return new LogService();
    }

    @Scope("singleton")
    @Bean
    public AdditionalMaterialView materialsView() {
        return new AdditionalMaterialView(materialService(), materialRepo());
    }

    @Scope("singleton")
    @Bean
    public HomeworkView homeworkView() {
        return new HomeworkView(homeworkService(), homeworkRepo());
    }

    @Scope("singleton")
    @Bean
    public PersonView personView() {
        return new PersonView(personService(), personRepo());
    }

    @Scope("singleton")
    @Bean
    public LectureView lectureView() {
        return new LectureView(lectureService(), lectureRepo());
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
