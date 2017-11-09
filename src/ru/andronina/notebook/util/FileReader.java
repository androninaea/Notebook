package ru.andronina.notebook.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.andronina.notebook.model.Employee;
import ru.andronina.notebook.model.Manager;
import ru.andronina.notebook.model.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    List<Person> peopleFromXML = new ArrayList<>();

    public void readFile(String fileName) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder parser = factory.newDocumentBuilder();
        Document document = parser.parse(fileName);
        Element people = document.getDocumentElement();
        NodeList person = people.getElementsByTagName("person"); //get all elements "person"
        for (int i = 0; i < person.getLength(); i++) {
            Element item = (Element) person.item(i);
            if (item.getElementsByTagName("type").item(0).getTextContent().equals("Employee")) { //check element content "type"
                Employee employee = getEmployeeFromXML(item);
                peopleFromXML.add(employee);
            } else {
                Manager manager = getManagerFromXML(item);
                peopleFromXML.add(manager);
            }
        }
    }

    private Manager getManagerFromXML(Element item) {
        //get element 'manager' from file, create object and write element content in object
        Manager manager = new Manager();
        manager.setId(item.getAttribute("id"));
        manager.setName(item.getElementsByTagName("name").item(0).getTextContent());
        manager.setSurname(item.getElementsByTagName("surname").item(0).getTextContent());
        manager.setYearOfBirth(Integer.valueOf(item.getElementsByTagName("yearOfBirth").item(0).getTextContent()));
        manager.setPhoneNumber(item.getElementsByTagName("phone").item(0).getTextContent());
        manager.setDepartmentName(item.getElementsByTagName("department").item(0).getTextContent());
        return manager;
    }

    private Employee getEmployeeFromXML(Element item) {
        //get element 'employee' from file, create object and write element content in object
        Employee employee = new Employee();
        employee.setId(item.getAttribute("id"));
        employee.setName(item.getElementsByTagName("name").item(0).getTextContent());
        employee.setSurname(item.getElementsByTagName("surname").item(0).getTextContent());
        employee.setYearOfBirth(Integer.valueOf(item.getElementsByTagName("yearOfBirth").item(0).getTextContent()));
        employee.setPhoneNumber(item.getElementsByTagName("phone").item(0).getTextContent());
        employee.setManagerName(item.getElementsByTagName("managerName").item(0).getTextContent());
        return employee;
    }

    public List<Person> getPeopleFromXML() {
        return peopleFromXML;
    }
}
