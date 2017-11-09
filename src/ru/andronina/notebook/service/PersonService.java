package ru.andronina.notebook.service;

import ru.andronina.notebook.model.Employee;
import ru.andronina.notebook.model.Manager;
import ru.andronina.notebook.model.Person;
import ru.andronina.notebook.repository.EmployeeRepository;
import ru.andronina.notebook.repository.ManagerRepository;

import java.util.List;
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

    public Person getPersonByCriteria(String name, String surname, String phoneNumber) {
        for (Person person : people) {
            if (person.getName().equals(name) && person.getSurname().equals(surname) && person.getPhoneNumber().equals(phoneNumber)) {
                return person;
            }
        }
        return null;
    }

    public void removePerson(Person person) {
        if (person.getClass().getSimpleName().equals("Employee")) {
            removeEmployee((Employee) person);
        } else removeManager((Manager) person);

    }

    private void removeManager(Manager manager) {
        managerRepository.delete(manager);
    }

    private void removeEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public void loadPeopleInRepository(List<Person> people) {
        for (Person p : people) {
            if (p.getClass().getSimpleName().equals("Employee")) {
                employeeRepository.add((Employee) p);
            } else managerRepository.add((Manager) p);
        }
    }
}
