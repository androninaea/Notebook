package ru.andronina.notebook.util;

import ru.andronina.notebook.builder.EmployeeBuilder;
import ru.andronina.notebook.builder.ManagerBuilder;
import ru.andronina.notebook.model.Person;
import ru.andronina.notebook.service.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    PersonService personService = new PersonService();

    public void printMenu() {
        while (true) {
            System.out.println("Enter operation number");
            System.out.println("1. View all employees");
            System.out.println("2. Create new employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Find employee");
            System.out.println("5. Exit");

            switch (readValue().nextInt()) {
                case 1:
                    printAllPeople();
                    break;
                case 2:
                    printTypesOfEmployee();
                    int number = readValue().nextInt();
                    if (number == 1) {
                        personService.createEmployee(new EmployeeBuilder().build());
                    } else if (number == 2) {
                        personService.createManager(new ManagerBuilder().build());
                    } else System.out.println("Invalid operation number");
                    break;
                case 3:
                    printAllPeople();
                    System.out.println("Enter employee number");
                    deletePerson(readValue().nextInt());
                    break;
                case 4:
                    printAndWriteSearchCriteria();
                    break;
                case 5:
                    save();
                    return;
                default:
                    System.out.println("Invalid operation number");
            }
        }
    }


    private void printTypesOfEmployee() {
        System.out.println("1. Employee");
        System.out.println("2. Manager");
    }

    private Person createEmployee(int number) {
        if (number == 1) {
            return new EmployeeBuilder().build();
        } else if (number == 2) {
            return new ManagerBuilder().build();
        }
        return null;
    }

    private void printAndWriteSearchCriteria() {
        System.out.println("Enter employee name: ");
        String name = readValue().nextLine();
        System.out.println("Enter employee surname: ");
        String surname = readValue().nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = readValue().nextLine();
        if (personService.getPeopleByCriteria(name, surname, phoneNumber).size() == 0)
            System.out.println("Employee not found");
    }

    private Scanner readValue() {
        return new Scanner(System.in);
    }

    private void save() {

    }

    private void printAllPeople() {
        if (personService.getAllPeople().size() == 0) System.out.println("List is empty");
        System.out.println();
        int i = 1;
        for (Person person : personService.getAllPeople()) {
            System.out.println(i + ". " + person);
            i++;
        }
    }

    private void deletePerson(int index) {
        List<Person> people = new ArrayList<Person>();
        for (Person person : personService.getAllPeople()) {
            people.add(person);
        }
        personService.removePerson(people.get(index - 1));
    }
}

