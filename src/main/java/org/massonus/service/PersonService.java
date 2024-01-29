package org.massonus.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.massonus.entity.Course;
import org.massonus.entity.Lecture;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.massonus.repo.CourseRepo;
import org.massonus.repo.LectureRepo;
import org.massonus.repo.PersonRepo;
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
public class PersonService implements UniversalService<Person> {

    private final PersonRepo personRepo;
    private final CourseRepo courseRepo;
    private final LectureService lectureService;
    private final LectureRepo lectureRepo;

    @Autowired
    public PersonService(PersonRepo personRepo, CourseRepo courseRepo, LectureService lectureService, LectureRepo lectureRepo) {
        this.personRepo = personRepo;
        this.courseRepo = courseRepo;
        this.lectureService = lectureService;
        this.lectureRepo = lectureRepo;
    }

    Person person;

    public Person createElementByUserForm(final String firstName, final String lastName, final String phone, final String email, Role role, final List<Integer> lectureIdList, final List<Integer> courseIdList) {
        person = new Person();

        person.setFirstName(firstName);

        person.setLastName(lastName);

        person.setPhone(phone);

        person.setEmail(email);

        person.setRole(role);

        personRepo.save(person);

        final List<Lecture> lectures = lecturesForPerson(person, lectureIdList);
        person.setLectures(lectures);

        final List<Course> courses = coursesForPerson(person, courseIdList);
        person.setCourses(courses);

        return person;
    }

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

        personRepo.save(person);

        List<Lecture> lectures = lecturesForPerson(person);
        person.setLectures(lectures);

        List<Course> courses = coursesForPerson(person);
        person.setCourses(courses);

        return person;
    }

    public Person createElementAuto(final Course course) {
        person = new Person();
        Random random = new Random();
        long id = random.nextInt(1, 50);

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
        personRepo.save(person);
        person.setLectures(createAndFillLecturesForPerson(course, person));

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

        List<Lecture> lectures = lecturesForPerson(person);
        person.setLectures(lectures);

        List<Course> courses = coursesForPerson(person);
        person.setCourses(courses);

        personRepo.save(person);

        return person;
    }

    private List<Course> coursesForPerson(Person person) {
        List<Course> courses = new ArrayList<>();

        System.out.println("How many courses?");
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();

        System.out.println("Choose courses: ");

        courseRepo.findAll().forEach(System.out::println);
        for (int i = 0; i < length; i++) {
            Scanner scanner1 = new Scanner(System.in);
            Course courseById = courseRepo.findById(scanner1.nextLong()).get();
            courseById.getPeople().add(person);
            courseRepo.save(courseById);
            courses.add(courseById);
        }
        return courses;
    }
    private List<Course> coursesForPerson(final Person person, final List<Integer> courseIdList) {
        List<Course> courses = new ArrayList<>();

        for (Integer id : courseIdList) {
            Course courseById = courseRepo.findById(id.longValue()).get();
            courseById.getPeople().add(person);
            courseRepo.save(courseById);
            courses.add(courseById);
        }
        return courses;
    }

    private List<Lecture> lecturesForPerson(final Person person, final List<Integer> lectureIdList) {
        List<Lecture> lectures = new ArrayList<>();

        for (Integer id : lectureIdList) {
            Lecture lectureById = lectureService.getLectureById(id).get();
            lectureById.setPerson(person);
            lectureService.saveLecture(lectureById);
            lectures.add(lectureById);
        }
        return lectures;
    }

    private List<Lecture> lecturesForPerson(final Person person) {
        List<Lecture> lectures = new ArrayList<>();

        System.out.println("How many lectures?");
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();

        System.out.println("Choose lectures: ");

        lectureRepo.findAll().forEach(System.out::println);

        for (int i = 0; i < length; i++) {
            Scanner scanner1 = new Scanner(System.in);
            Lecture lectureById = lectureRepo.findById(scanner1.nextLong()).get();
            lectureById.setPerson(person);
            lectureRepo.save(lectureById);
            lectures.add(lectureById);
        }
        return lectures;
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

    public Person getById(List<Person> list, long id) {

        List<Person> collect = list.stream()
                .filter(p -> p.getId().equals(id))
                .toList();

        return collect.get(0);
    }

    public void savePerson(final Person person) {
        personRepo.save(person);
    }

    public List<Person> getPeopleList() {
        return personRepo.findAll();
    }

    public Optional<Person> getPersonById(final long id) {
        return personRepo.findById(id);
    }

    public void deletePerson(final long id) {
        personRepo.deleteById(id);
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
