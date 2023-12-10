package org.massonus.repo;

import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.service.PersonService;

import java.util.*;

public class PersonRepo implements UniversalRepository {

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
}
