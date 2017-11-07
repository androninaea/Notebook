package ru.andronina.notebook.builder;

import ru.andronina.notebook.model.Manager;

import java.util.GregorianCalendar;

public class ManagerBuilder implements Builder {
    private static final int MAX_YEAR = new GregorianCalendar().getWeekYear() - 18;
    private static final int MIN_YEAR = new GregorianCalendar().getWeekYear() - 65;

    @Override
    public Manager build() {
        Manager manager = new Manager();
        System.out.println("Enter name: ");
        manager.setName(readValue().nextLine());
        System.out.println("Enter surname: ");
        manager.setSurname(readValue().nextLine());
        while (true) {
            System.out.println("Enter the year of birth: ");
            int year = readValue().nextInt();
            if (check(year)) {
                manager.setYearOfBirth(year);
                break;
            } else System.out.println("Invalid year of birth");
        }
        System.out.println("Enter phone number: ");
        manager.setPhoneNumber(readValue().nextLine());
        System.out.println("Enter department name: ");
        manager.setDepartmentName(readValue().nextLine());
        return manager;
    }

    private boolean check(int year) {
        if (year >= MIN_YEAR && year <= MAX_YEAR) {
            return true;
        }
        return false;
    }
}
