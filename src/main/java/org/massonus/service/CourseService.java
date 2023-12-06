package org.massonus.service;

import org.massonus.entity.Course;

import java.io.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class CourseService {
    static int courseId;
    static String courseName;
    Course course;

    public Course createElementByUser() {
        course = new Course();
        course.setId(courseId);
        course.setName(courseName);

        return course;
    }

    public Course createElementAuto() {
        course = new Course();
        course.setId(courseId);

        if (courseId == 1) {
            course.setName("First");
        } else if (courseId == 2) {
            course.setName("Second");
        } else if (courseId == 3) {
            course.setName("Third");
        } else if (courseId == 4) {
            course.setName("Fourth");
        } else if (courseId == 5) {
            course.setName("Fives");
        }
        return course;
    }

    public static void createCourseIdByUser() {
        System.out.println("Enter course id");
        Scanner scanner = new Scanner(System.in);
        try {
            courseId = scanner.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println("Incorrect id, use only numbers");
            createCourseIdByUser();
        }
    }

    public static void createCourseIdAuto() {
        Random random = new Random();
        courseId = random.nextInt(1, 5);
    }

    public static void createCourseNameByUser() {
        System.out.println("Enter course name");
        Scanner scanner = new Scanner(System.in);
        courseName = scanner.nextLine();
    }

    public void serial(Course course) {
        final File file = new File("src/main/java/org/massonus/service/serialization.txt");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(course);
            System.out.println("serial completed successfully \n" + course);
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
    }

    public Course deSer() {
        final File file = new File("src/main/java/org/massonus/service/serialization.txt");
        Course deSer = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            deSer = (Course) inputStream.readObject();
            System.out.println("deSer completed successfully ");
        } catch (IOException | ClassNotFoundException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
        return deSer;
    }
}

