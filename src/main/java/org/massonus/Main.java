package org.massonus;

import org.massonus.repo.*;
import org.massonus.service.*;
import org.massonus.view.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        AdditionalMaterialRepo materialRepo = context.getBean(AdditionalMaterialRepo.class);
        AdditionalMaterialService materialService = context.getBean(AdditionalMaterialService.class);
        AdditionalMaterialView materialView = context.getBean(AdditionalMaterialView.class);

        CourseRepo courseRepo = context.getBean(CourseRepo.class);
        CourseService courseService = context.getBean(CourseService.class);
        CourseView courseView = context.getBean(CourseView.class);

        HomeworkRepo homeworkRepo = context.getBean(HomeworkRepo.class);
        HomeworkService homeworkService = context.getBean(HomeworkService.class);
        HomeworkView homeworkVie = context.getBean(HomeworkView.class);

        LectureRepo lectureRepo = context.getBean(LectureRepo.class);
        LectureService lectureService = context.getBean(LectureService.class);
        LectureView lectureView = context.getBean(LectureView.class);

        PersonRepo personRepo = context.getBean(PersonRepo.class);
        PersonService personService = context.getBean(PersonService.class);
        PersonView personView = context.getBean(PersonView.class);

        courseView.firstInitialize();
        courseView.mainMenu();

    }
}
