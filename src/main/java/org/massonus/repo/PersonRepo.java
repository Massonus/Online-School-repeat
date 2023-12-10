package org.massonus.repo;

import lombok.Getter;
import org.massonus.entity.Person;
import org.massonus.log.Logger;
import org.massonus.service.PersonService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonRepo implements UniversalRepository {

    @Getter
    public List<Person> people;
    private Set<Person> personSet;
    private final PersonService personService = new PersonService();
    private final Logger logger = new Logger("LectureRepo");

    public List<Person> createAndFillListAuto(int lengthMas) {

        personSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            personSet.add(personService.createElementAuto());
        }
        people = new ArrayList<>(personSet);
        logger.info("List created successful, size : " + lengthMas);
        return people;
    }

    public List<Person> createAndFillListByUser(int lengthMas) {
        System.out.println("Person:");
        personSet = new HashSet<>();
        for (int i = 0; i < lengthMas; i++) {
            personSet.add(personService.createElementByUser());
        }
        people = new ArrayList<>(personSet);
        logger.info("List created successful, size : " + lengthMas);
        return people;
    }
}
