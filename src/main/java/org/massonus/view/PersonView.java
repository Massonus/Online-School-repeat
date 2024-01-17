package org.massonus.view;

import org.massonus.entity.Course;
import org.massonus.entity.Person;
import org.massonus.service.CourseService;
import org.massonus.service.PersonService;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PersonView {
    private final PersonService personService;

    private final CourseService courseService = new CourseService();

    public PersonView(PersonService personService) {
        this.personService = personService;
    }

    public void workWithPeople(List<Course> courses) {
        List<Person> people = courseService.getAllPeople(courses);

        while (true) {
            System.out.println("\n What you want to do?");
            System.out.println("1. Print all People");
            System.out.println("2. Add new Person");
            System.out.println("3. To remove element");
            System.out.println("4. To check that array is Empty");
            System.out.println("5. To get size of array");
            System.out.println("6. To sort by last name");
            System.out.println("7. To print all people where LastName until N");
            System.out.println("8. To write emails to the file");
            System.out.println("9. To print emails and First and Last name");
            System.out.println("0. To return");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    personService.printAll(people);
                    break;

                case "2":
                    /*Person newElement = personService.createElementByUser();
                    personService.add(newElement, people, courseId);
                    break;*/

                case "3":
                    int id = personService.choiceId();
                    personService.removeById(people, id);
                    break;

                case "4":
                    System.out.println(people.isEmpty());
                    break;

                case "5":
                    System.out.println(people.size());
                    break;

                case "6":
                    Collections.sort(people);
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
