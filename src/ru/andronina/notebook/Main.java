package ru.andronina.notebook;

import ru.andronina.notebook.util.Menu;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


class Main {
    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        new Menu().printMenu();
    }
}