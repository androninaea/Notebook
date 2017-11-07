package ru.andronina.notebook.builder;

import ru.andronina.notebook.model.Employee;

import java.util.GregorianCalendar;

public class EmployeeBuilder implements Builder {
    private static final int MAX_YEAR = new GregorianCalendar().getWeekYear() - 18;
    private static final int MIN_YEAR = new GregorianCalendar().getWeekYear() - 65;

    @Override
    public Employee build() {
        Employee employee = new Employee();
        System.out.println("Enter name: ");
        employee.setName(readValue().nextLine());
        System.out.println("Enter surname: ");
        employee.setSurname(readValue().nextLine());
        while (true) {
            System.out.println("Enter the year of birth: ");
            int year = readValue().nextInt();
            if (check(year)) {
                employee.setYearOfBirth(year);
                break;
            } else System.out.println("Invalid year of birth");
        }
        System.out.println("Enter phone number: ");
        employee.setPhoneNumber(readValue().nextLine());
        System.out.println("Enter the name of the manager: ");
        employee.setManagerName(readValue().nextLine());
        return employee;
    }

    private boolean check(int year) {
        if (year >= MIN_YEAR && year <= MAX_YEAR) {
            return true;
        }
        return false;
    }
}
