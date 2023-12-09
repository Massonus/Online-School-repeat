package org.massonus.repo;

import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.service.PersonService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PersonRepo implements AboutRepo<Person> {

    private Set<Person> personSet;
    private List<Person> people;
    private final PersonService personService = new PersonService();
    private final Logger logger = new Logger("LectureRepo");

    public List<Person> createAndFillListAuto() {
        Random random = new Random();
        int lengthMas = random.nextInt(1, 30);
        personSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            personSet.add(personService.createElementAuto());
        }
        people = new ArrayList<>(personSet);
        logger.info("List created successful, size : " + lengthMas);
        return people;
    }

    public List<Person> createAndFillListByUser() {
        System.out.println("Person:");
        int lengthMas = lengthMas();
        personSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            personSet.add(personService.createElementByUser());
        }
        people = new ArrayList<>(personSet);
        logger.info("List created successful, size : " + lengthMas);
        return people;
    }

    public void add() {
        Person element;
        if (choice().equals("2")) {
            element = personService.createElementAuto();
        } else {
            element = personService.createElementByUser();
        }
        boolean isExist = people.stream()
                .map(Person::getEmail)
                .anyMatch(m -> m.equals(element.getEmail()));
        if (isExist) {
            System.out.println("This email is already exist");
            return;
        }
        people.add(element);
        logger.info("added: " + element);
    }

    public void add(int index) {
        Person element;
        if (choice().equals("2")) {
            element = personService.createElementAuto();
        } else {
            element = personService.createElementByUser();
        }
        boolean isExist = people.stream()
                .map(Person::getEmail)
                .anyMatch(m -> m.equals(element.getEmail()));
        if (isExist) {
            System.out.println("This email is already exist");
            return;
        }
        people.add(index, element);
        logger.info("added: " + element);
    }

    public void writeEmailsToTheFile(List<String> collect) {
        Path path = Path.of("src/org.massonus.view/emails.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(collect.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
