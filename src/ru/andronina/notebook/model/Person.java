package ru.andronina.notebook.model;

import java.util.Objects;

public class Person implements Comparable<Person> {

    private String id;
    private String name;
    private String surname;
    private int yearOfBirth;
    private String phoneNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Person o) { //sort elements with type "Person" by surname and year of birth
        int result = Objects.compare(getSurname(), o.getSurname(), String::compareTo);
        if (result != 0) {
            return result;
        }
        return Objects.compare(getYearOfBirth(), o.getYearOfBirth(), Integer::compareTo);
    }
}
