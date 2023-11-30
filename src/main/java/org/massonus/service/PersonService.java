package org.massonus.service;

import org.massonus.entity.Person;
import org.massonus.entity.Role;

import java.util.Random;
import java.util.Scanner;

public class PersonService {

    Person person;

    public Person createElementByUser() {
        System.out.println("Then you must create the Person");
        person = new Person();
        System.out.println("Enter id of the Person");
        Scanner scanner = new Scanner(System.in);
        person.setId(scanner.nextInt());

        System.out.println("Enter first name of the Person");
        Scanner scanner1 = new Scanner(System.in);
        person.setFirstName(scanner1.nextLine());

        System.out.println("Enter last name of the Person");
        Scanner scanner2 = new Scanner(System.in);
        person.setLastName(scanner2.nextLine());

        System.out.println("Enter phone of the Person");
        Scanner scanner3 = new Scanner(System.in);
        person.setPhone(scanner3.nextLine());

        System.out.println("Enter email of the Person");
        Scanner scanner4 = new Scanner(System.in);
        person.setEmail(scanner4.nextLine());

        System.out.println("1. To select the role Student");
        System.out.println("2. To select the role Teacher");
        Scanner scanner5 = new Scanner(System.in);
        int role = scanner5.nextInt();
        if (role == 1) {
            person.setRole(Role.STUDENT);
        } else if (role == 2) {
            person.setRole(Role.TEACHER);
        } else {
            System.out.println("Incorrect");
        }
        person.setCourseId(CourseService.courseId);

        return person;
    }

    public Person createElementAuto() {
        person = new Person();
        Random random = new Random();
        int id = random.nextInt(1, 50);
        person.setId(id);

        if (id < 10 || id > 40) {
            person.setFirstName("John");
            person.setLastName("Smith");
            person.setPhone("Samsung");
            person.setEmail("johnson31@gmail.com");
            person.setRole(Role.TEACHER);
        } else if (id < 20 || id > 30) {
            person.setFirstName("Nick");
            person.setLastName("Nikolos");
            person.setPhone("Xiaomi");
            person.setEmail("adams85@gmail.com");
            person.setRole(Role.STUDENT);
        } else {
            person.setFirstName("Max");
            person.setLastName("Collins");
            person.setPhone("iPhone");
            person.setEmail("collins245@gmail.com");
            person.setRole(Role.TEACHER);
        }
        person.setCourseId(CourseService.courseId);

        return person;
    }
}
