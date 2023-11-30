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

    Set<Person> personSet;
    public static List<Person> people;
    PersonService personService = new PersonService();
    final Logger logger = new Logger("LectureRepo");

    public void createAndFillListAuto() {
        Random random = new Random();
        int lengthMas = random.nextInt(1, 69);
        personSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            personSet.add(personService.createElementAuto());
        }
        people = new ArrayList<>(personSet);
        logger.info("List created successful, size : " + lengthMas);
    }

    public void createAndFillListByUser() {
        System.out.println("Person:");
        int lengthMas = lengthMas();
        for (int i = 0; i < lengthMas; i++) {
            people.add(personService.createElementByUser());
        }
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
}
