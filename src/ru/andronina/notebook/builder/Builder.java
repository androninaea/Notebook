package ru.andronina.notebook.builder;

import ru.andronina.notebook.model.Person;

import java.util.Scanner;

public interface Builder {
    Person build();

    default Scanner readValue() {
        return new Scanner(System.in);
    }
}
