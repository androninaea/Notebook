package ru.andronina.notebook.repository;

import ru.andronina.notebook.model.Employee;

import java.util.*;

public class EmployeeRepository implements AbstractRepository<Employee, String> {
    private final Map<String, Employee> employees = new TreeMap<>();

    @Override
    public void add(Employee value) {
        String key = UUID.randomUUID().toString();
        if (value.getId() == null) {
            value.setId(key);
            employees.put(key, value);
        } else employees.put(value.getId(), value);

    }

    @Override
    public void delete(Employee value) {
        employees.remove(value.getId());
    }

    @Override
    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    @Override
    public Employee findByID(String key) {
        return employees.getOrDefault(key, Employee.EMPTY);
    }

}
