package org.massonus.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.massonus.entity.Person;
import org.massonus.entity.Role;
import org.massonus.repo.PersonRepo;
import org.massonus.repo.UniversalRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonService implements UniversalService<Person>, UniversalRepository {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(PersonService.class.getResource("/log4j.xml").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    Person person;

    public Person createElementByUser() {
        System.out.println("Then you must create the Person");
        person = new Person();

        int size = personRepo.getAllPeople().size();
        person.setId(size + 1);

        System.out.println("Enter course id of the Person");
        Scanner scanner = new Scanner(System.in);
        person.setCourseId(scanner.nextInt());

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

        return person;
    }

    public String generateRandomString() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }


    public boolean add(Person elementByUser, List<Person> people) {
        insertPersonIntoDatabase(elementByUser);
        logger.info("added: " + elementByUser);
        return people.add(elementByUser);
    }

    public void add(Person person) {
        int size = personRepo.getAllPeople().size();
        person.setId(size + 1);
        insertPersonIntoDatabase(person);
    }

    public boolean removeById(List<Person> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            Person element = list.get(i);
            if (id == element.getId()) {
                System.out.println(list.get(i));
                Person remove = list.remove(i);
                deletePersonFromDatabase(id);
                logger.info("element removed " + remove);
                return true;
            }
        }
        return false;
    }

    private void insertPersonIntoDatabase(final Person person) {
        try {
            String sql = "INSERT INTO public.person(id, first_name, last_name, phone, email, role, course_id)VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setInt(1, person.getId());
                preparedStatement.setString(2, person.getFirstName());
                preparedStatement.setString(3, person.getLastName());
                preparedStatement.setString(4, person.getPhone());
                preparedStatement.setString(5, person.getEmail());
                preparedStatement.setString(6, person.getRole().toString());
                preparedStatement.setInt(7, person.getCourseId());


                int rows = preparedStatement.executeUpdate();
                System.out.println("add Lines Person: " + rows);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
    }

    private void deletePersonFromDatabase(int id) {
        try {
            final String sql = "DELETE FROM public.person WHERE id=" + id;
            try (Connection conn = createCon();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed..." + ex);
        }
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
                .collect(Collectors.toList());
    }

    public void printEmailAndFullName(List<Person> people) {
        people.stream()
                .map(a -> a.getFirstName() + " " + a.getLastName() + ": " + a.getEmail())
                .forEach(System.out::println);
    }


}
