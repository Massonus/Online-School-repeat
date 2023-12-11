package org.massonus.view;

import org.massonus.entity.Person;
import org.massonus.service.PersonService;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PersonView {
    private static final PersonService personService = new PersonService();

    public void workWithPerson(List<Person> people) {

        while (true) {
            System.out.println("\n What you want to do?");
            System.out.println("1. Print all People");
            System.out.println("2. Add new Person by index");
            System.out.println("3. Add new Person to the end");
            System.out.println("4. To remove element");
            System.out.println("5. To check that array is Empty");
            System.out.println("6. To get size of array");
            System.out.println("7. To sort by last name");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    personService.printAll(people);
                    break;

                case "2":
                    int index = personService.choiceIndex();
                    personService.add(people, index);
                    break;

                case "3":
                    personService.add(people);
                    break;

                case "4":
                    int id = personService.choiceId();
                    personService.removeById(people, id);
                    break;

                case "5":
                    System.out.println(people.isEmpty());
                    break;

                case "6":
                    System.out.println(people.size());
                    break;

                case "7":
                    Collections.sort(people);
                    break;

                case "0":
                    return;
            }
        }
    }

    public static void workWithAllPeople(List<Person> allPeople) {

        while (true) {
            System.out.println("\n1. To print all people as List");
            System.out.println("2. To print all people where LastName until N");
            System.out.println("3. To write emails to the file");
            System.out.println("4. To print emails and First and Last name");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    allPeople.forEach(System.out::println);
                    break;

                case "2":
                    personService.printFilteredEmails(allPeople);
                    break;

                case "3":
                    List<String> emails = personService.emailsToList(allPeople);
                    personService.writeEmailsToTheFile(emails);
                    break;

                case "4":
                    personService.printEmailAndFullName(allPeople);
                    break;

                case "0":
                    return;
            }
        }
    }
}
