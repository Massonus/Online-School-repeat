package org.massonus.view;

import org.massonus.entity.Person;
import org.massonus.repo.PersonRepo;
import org.massonus.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class PersonView {
    private final PersonService personService;
    private final PersonRepo personRepo;

    @Autowired
    public PersonView(PersonService personService, PersonRepo personRepo) {
        this.personService = personService;
        this.personRepo = personRepo;
    }

    public void workWithPeople() {

        while (true) {
            List<Person> people = personRepo.getPeopleList();

            System.out.println("\n What you want to do?");
            System.out.println("1. Print all People");
            System.out.println("2. Add new Person");
            System.out.println("3. Delete element");
            System.out.println("4. Get size of array");
            System.out.println("5. Sort by id");
            System.out.println("6. Sort by last name");
            System.out.println("7. Print all people where LastName until N");
            System.out.println("8. Write emails to the file");
            System.out.println("9. Print emails and First and Last name");
            System.out.println("0. Return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    personService.printAll(people);
                    break;

                case "2":
                    Person newElement = personService.createElementByUser();
                    personRepo.addPerson(newElement);
                    break;

                case "3":
                    int id = personService.choiceId();
                    Person personById = personRepo.getPersonById(id);
                    personRepo.deletePerson(personById);
                    break;

                case "4":
                    System.out.println(people.size());
                    break;

                case "5":
                    people = personService.sortPeopleById(people);
                    System.out.println(people);
                    break;

                case "6":
                    Collections.sort(people);
                    System.out.println(people);
                    break;

                case "7":
                    personService.printFilteredEmails(people);
                    break;

                case "8":
                    List<String> emails = personService.emailsToList(people);
                    personService.writeEmailsToTheFile(emails);
                    break;

                case "9":
                    personService.printEmailAndFullName(people);
                    break;

                case "0":
                    return;
            }
        }
    }
}
