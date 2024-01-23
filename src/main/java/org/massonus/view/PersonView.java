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
        List<Person> people = personRepo.getPeopleList();
        while (true) {

            System.out.println("\n What you want to do?");
            System.out.println("1. Print all People");
            System.out.println("2. Add new Person");
            System.out.println("3. Change information about person by id");
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
                    people = personRepo.getPeopleList();
                    personService.printAll(people);
                    break;

                case "2":
                    Person newElement = personService.createElementByUser();
                    personRepo.addPerson(newElement);
                    break;

                case "3":
                    int id = personService.choiceId();
                    Person person = personService.personRefactor(personRepo.getPersonById(id));
                    personRepo.updatePerson(person);
                    break;

                case "4":
                    Person personById = personRepo.getPersonById(personService.choiceId());
                    personRepo.deletePerson(personById);
                    break;

                case "5":
                    System.out.println(people.size());
                    break;

                case "6":
                    people = personService.sortPeopleById(people);
                    System.out.println(people);
                    break;

                case "7":
                    Collections.sort(people);
                    System.out.println(people);
                    break;

                case "8":
                    personService.printFilteredEmails(people);
                    break;

                case "9":
                    List<String> emails = personService.emailsToList(people);
                    personService.writeEmailsToTheFile(emails);
                    break;

                case "10":
                    personService.printEmailAndFullName(people);
                    break;

                case "0":
                    return;
            }
        }
    }
}
