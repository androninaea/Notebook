package ru.andronina.notebook.util;

import ru.andronina.notebook.builder.EmployeeBuilder;
import ru.andronina.notebook.builder.ManagerBuilder;
import ru.andronina.notebook.model.Person;
import ru.andronina.notebook.service.PersonService;

import java.io.File;
import java.util.*;

public class Menu {
    public final static String FILE_NAME = "notebook.xml";
    PersonService personService = new PersonService();

    public void printMenu() throws Exception {
        loadPeople();
        while (true) {
            System.out.println("Enter operation number: ");
            System.out.println("1. View all employees");
            System.out.println("2. Create new employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Find employee");
            System.out.println("5. Exit");
            try {
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
                        } else System.out.println("Invalid operation number\n");
                        break;
                    case 3:
                        printAllPeople();
                        System.out.println("Enter employee number: ");
                        try {
                            int personNumber = readValue().nextInt();
                            if (personNumber >= 1 && personNumber <= personService.getAllPeople().size()) {
                                deletePerson(personNumber);
                            } else System.out.println("Invalid operation number\n");
                        } catch (InputMismatchException ex) {
                            System.out.println("Invalid operation number\n");
                        }
                        break;
                    case 4:
                        printAndWriteSearchCriteria();
                        break;
                    case 5:
                        save(personService.getAllPeople());
                        return;
                    default:
                        System.out.println("Invalid operation number\n");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid operation number\n");
            }

        }
    }


    private void printTypesOfEmployee() {
        System.out.println("1. Employee");
        System.out.println("2. Manager");
    }

    private void printAndWriteSearchCriteria() {
        System.out.println("Enter employee name: ");
        String name = readValue().nextLine();
        System.out.println("Enter employee surname: ");
        String surname = readValue().nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = readValue().nextLine();
        Person foundPerson = personService.getPersonByCriteria(name, surname, phoneNumber);
        if (foundPerson == null)
            System.out.println("Employee not found \n");
        else {
            System.out.println("\n" + foundPerson);
        }
    }

    private Scanner readValue() {
        return new Scanner(System.in);
    }

    private void save(Set<Person> people) throws Exception {
        new FileWriter().writeToXML(people);
    }

    private void printAllPeople() {
        if (personService.getAllPeople().size() == 0) System.out.println("List is empty \n");
        System.out.println();
        int i = 1;
        for (Person person : personService.getAllPeople()) {
            System.out.println(i + ". " + person);
            i++;
        }
    }

    private void deletePerson(int index) {
        List<Person> people = new ArrayList<Person>(); //remove person by index
        for (Person person : personService.getAllPeople()) {
            people.add(person);
        }
        personService.removePerson(people.get(index - 1));
    }

    private void loadPeople() throws Exception {
        if (new File(FILE_NAME).exists()) { //load elements from "notebook.xml" if this file exists
            FileReader fileReader = new FileReader();
            fileReader.readFile(FILE_NAME);
            List<Person> people = fileReader.getPeopleFromXML();
            personService.loadPeopleInRepository(people);
        }
    }
}

