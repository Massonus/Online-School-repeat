package org.massonus.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.massonus.entity.Person;
import org.massonus.entity.Role;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PersonService implements UniversalService<Person> {

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
            person.setRole(Role.TEACHER);
        } else if (id < 20 || id > 30) {
            person.setFirstName("Nick");
            person.setLastName("Nikolos");
            person.setPhone("Xiaomi");

            person.setRole(Role.STUDENT);
        } else {
            person.setFirstName("Max");
            person.setLastName("Collins");
            person.setPhone("iPhone");
            person.setRole(Role.TEACHER);
        }
        person.setEmail(generateRandomString() + "@gmail.com");
        person.setCourseId(CourseService.courseId);

        return person;
    }

    public String generateRandomString() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }


    public boolean add(List<Person> people) {
        if (choice().equals("2")) {
            person = createElementAuto();
        } else {
            person = createElementByUser();
        }
        boolean isExist = people.stream()
                .map(Person::getEmail)
                .anyMatch(m -> m.equals(person.getEmail()));
        if (isExist) {
            System.out.println("This email is already exist");
            return false;
        }
        people.add(person);
        logger.info("added: " + person);
        return true;
    }

    public boolean add(List<Person> people, int index) {
        if (choice().equals("2")) {
            person = createElementAuto();
        } else {
            person = createElementByUser();
        }
        boolean isExist = people.stream()
                .map(Person::getEmail)
                .anyMatch(m -> m.equals(person.getEmail()));
        if (isExist) {
            System.out.println("This email is already exist");
            return false;
        }
        people.add(index, person);
        logger.info("added: " + person);
        return true;
    }

    public boolean removeById(List<Person> list, int id) {
        if (list == null) {
            System.out.println("Please create the List");
            logger.warning("array is empty");
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            Person element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                Person remove = list.remove(i);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    public Person getById(List<Person> list, int id) {
        if (list == null) {
            System.out.println("Please create an Array");
            return null;
        }

        for (Person element : list) {
            if (id == element.getId()) {
                return element;
            }
        }
        return null;
    }

    public boolean writeEmailsToTheFile(List<String> collect) {
        Path path = Path.of("src/org.massonus.view/emails.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(collect.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void printFilteredEmails(List<Person> people) {
        people.stream()
                .filter(t -> !t.getLastName().startsWith("N"))
                .forEach(System.out::println);
    }

    public List<String> emailsToList(List<Person> people) {
        return people.stream()
                .map(Person::getEmail)
                .toList();
    }

    public void printEmailAndFullName(List<Person> people) {
        people.stream()
                .map(a -> a.getFirstName() + " " + a.getLastName() + ": " + a.getEmail())
                .forEach(System.out::println);
    }


}
