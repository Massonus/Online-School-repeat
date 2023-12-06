package org.massonus.view;

import org.massonus.entity.Person;
import org.massonus.repo.PersonRepo;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PersonView {
    final static PersonRepo personRepo = new PersonRepo();

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
                    personRepo.getAll(people);
                    break;

                case "2":
                    int index = personRepo.choiceIndex();
                    personRepo.add(index);
                    break;

                case "3":
                    personRepo.add();
                    break;

                case "4":
                    personRepo.removeById(people);
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
                    allPeople.stream()
                            .filter(t -> !t.getLastName().startsWith("N"))
                            .forEach(System.out::println);
                    break;

                case "3":
                    List<String> collect = allPeople.stream()
                            .map(Person::getEmail)
                            .toList();
                    personRepo.writeEmailsToTheFile(collect);
                    break;

                case "4":
                    List<String> list = allPeople.stream()
                            .map(a -> a.getFirstName() + " " + a.getLastName() + ": " + a.getEmail())
                            .toList();
                    list.forEach(System.out::println);
                    break;

                case "0":
                    return;
            }
        }
    }
}
