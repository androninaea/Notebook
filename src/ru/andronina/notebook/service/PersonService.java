package ru.andronina.notebook.service;

import ru.andronina.notebook.model.Employee;
import ru.andronina.notebook.model.Manager;
import ru.andronina.notebook.model.Person;
import ru.andronina.notebook.repository.EmployeeRepository;
import ru.andronina.notebook.repository.ManagerRepository;

import java.util.Set;
import java.util.TreeSet;

public class PersonService {
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private ManagerRepository managerRepository = new ManagerRepository();
    private Set<Person> people = new TreeSet<>();

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    private void fillListOfPeople() {
        people.clear();
        people.addAll(employeeRepository.getAll());
        people.addAll(managerRepository.getAll());
    }

    public Set<Person> getAllPeople() {
        fillListOfPeople();
        return people;
    }

    public void createManager(Manager manager) {
        managerRepository.add(manager);
    }

    public void createEmployee(Employee employee) {
        employeeRepository.add(employee);
    }

    public Set<Person> getPeopleByCriteria(String name, String surname, String phoneNumber) {
        Set<Person> peopleFound = new TreeSet<>();
        for (Person person : people) {
            if (person.getName().equals(name) && person.getSurname().equals(surname) && person.getPhoneNumber().equals(phoneNumber)) {
                peopleFound.add(person);
            }
        }
        return peopleFound;
    }

    public void removePerson(Person person) {
        for (Person p : managerRepository.getAll()) {
            if (p.getId().equals(person.getId())) managerRepository.delete((Manager) person);
        }
        for (Person p : employeeRepository.getAll()) {
            if (p.getId().equals(person.getId())) employeeRepository.delete((Employee) person);
        }

    }
}
