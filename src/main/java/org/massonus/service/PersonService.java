package org.massonus.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.massonus.entity.Course;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.massonus.repo.CourseRepo;
import org.massonus.repo.PersonRepo;
import org.massonus.repo.UniversalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService implements UniversalService<Person>, UniversalRepository {

    private final PersonRepo personRepo;
    private final LectureService lectureService;
    private final CourseRepo courseRepo;

    @Autowired
    public PersonService(PersonRepo personRepo, LectureService lectureService, CourseRepo courseRepo) {
        this.personRepo = personRepo;
        this.lectureService = lectureService;
        this.courseRepo = courseRepo;
    }

    Person person;

    public Person createElementByUser() {
        System.out.println("Then you must create the Person");
        person = new Person();

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

        return person;
    }

    public Person createElementAuto(final Course course) {
        person = new Person();
        Random random = new Random();
        int id = random.nextInt(1, 50);

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

        person.getCourses().add(course);
        personRepo.addPerson(person);
        List<Lecture> lecturesForPerson = createAndFillLecturesForPerson(course, person);
        person.setLectures(lecturesForPerson);

        return person;
    }

    public Person personRefactor(final Person person) {
        System.out.println("Change first name of the Person");
        Scanner scanner1 = new Scanner(System.in);
        person.setFirstName(scanner1.nextLine());

        System.out.println("Change last name of the Person");
        Scanner scanner2 = new Scanner(System.in);
        person.setLastName(scanner2.nextLine());

        System.out.println("Change phone of the Person");
        Scanner scanner3 = new Scanner(System.in);
        person.setPhone(scanner3.nextLine());

        System.out.println("Change email of the Person");
        Scanner scanner4 = new Scanner(System.in);
        person.setEmail(scanner4.nextLine());

        System.out.println("1. Change the role Student");
        System.out.println("2. Change the role Teacher");
        Scanner scanner5 = new Scanner(System.in);
        int role = scanner5.nextInt();
        if (role == 1) {
            person.setRole(Role.STUDENT);
        } else if (role == 2) {
            person.setRole(Role.TEACHER);
        } else {
            System.out.println("Incorrect");
        }

        List<Course> courses = coursesForPerson();
        person.setCourses(courses);

        person.setLectures(createAndFillLecturesForPerson(courses.get(0), person));


        return person;
    }

    private List<Course> coursesForPerson() {
        List<Course> courses = new ArrayList<>();

        System.out.println("How many courses?");
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();

        System.out.println("Choose courses: ");

        courseRepo.getCourseList().forEach(System.out::println);
        for (int i = 0; i < length; i++) {
            Scanner scanner1 = new Scanner(System.in);
            Course courseById = courseRepo.getCourseById(scanner1.nextInt());
            courses.add(courseById);
        }
        return courses;
    }

    private List<Lecture> createAndFillLecturesForPerson(final Course course, final Person person) {
        List<Lecture> materials = new ArrayList<>();
        Random random = new Random();
        int lengthMas = random.nextInt(1, 5);
        for (int i = 0; i < lengthMas; i++) {
            Lecture elementAuto = lectureService.createElementAuto(course, person);
            materials.add(elementAuto);
        }
        return materials;
    }

    private String generateRandomString() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public Person getById(List<Person> list, int id) {

        List<Person> collect = list.stream()
                .filter(p -> p.getId().equals(id))
                .toList();

        return collect.get(0);
    }

    public List<Person> sortPeopleById(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.toList());
    }

    public void printFilteredEmails(List<Person> people) {
        people.stream()
                .filter(t -> !t.getLastName().startsWith("N"))
                .forEach(System.out::println);
    }

    public List<String> emailsToList(List<Person> people) {
        return people.stream()
                .map(Person::getEmail)
                .collect(Collectors.toList());
    }

    public void printEmailAndFullName(List<Person> people) {
        people.stream()
                .map(a -> a.getFirstName() + " " + a.getLastName() + ": " + a.getEmail())
                .forEach(System.out::println);
    }

    public boolean writeEmailsToTheFile(List<String> collect) {
        Path path = Path.of("src/main/resources/emails.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(collect.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public List<String> getEmailsFromFile() {
        List<String> emails = new ArrayList<>();
        Path path = Paths.get("src/main/resources/emails.txt");

        try {
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String currentValue;
            while ((currentValue = reader.readLine()) != null) {
                emails.add(currentValue);
            }
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
        return emails;
    }

}
