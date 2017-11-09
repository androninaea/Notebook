package ru.andronina.notebook.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.andronina.notebook.model.Employee;
import ru.andronina.notebook.model.Manager;
import ru.andronina.notebook.model.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Set;

class FileWriter {
    private static final String FILE_NAME = "notebook.xml";
    private static final String EMPLOYEE_TYPE = "Employee";

    public void writeToXML(Set<Person> people) throws Exception {
        File file = new File(FILE_NAME);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Element root = doc.createElement("people");
        doc.appendChild(root);
        Element person;
        for (Person p : people) {
            if (p.getClass().getSimpleName().equals(EMPLOYEE_TYPE)) {
                person = createEmployeeElement((Employee) p, doc);
                root.appendChild(person);
            } else {
                person = createMangerElement((Manager) p, doc);
                root.appendChild(person);

            }
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
    }


    public Element createMangerElement(Manager manager, Document doc) {
        Element person = doc.createElement("person");
        person.setAttribute("id", manager.getId());
        Element name = doc.createElement("name");
        name.setTextContent(manager.getName());
        Element surname = doc.createElement("surname");
        surname.setTextContent(manager.getSurname());
        Element yearOfBirth = doc.createElement("yearOfBirth");
        yearOfBirth.setTextContent(manager.getYearOfBirth() + "");
        Element phone = doc.createElement("phone");
        phone.setTextContent(manager.getPhoneNumber());
        Element type = doc.createElement("type");
        type.setTextContent(manager.getClass().getSimpleName());
        Element department = doc.createElement("department");
        department.setTextContent(manager.getDepartmentName());
        person.appendChild(type);
        person.appendChild(name);
        person.appendChild(surname);
        person.appendChild(yearOfBirth);
        person.appendChild(phone);
        person.appendChild(department);
        return person;
    }

    public Element createEmployeeElement(Employee employee, Document doc) {
        Element person = doc.createElement("person");
        person.setAttribute("id", employee.getId());
        Element name = doc.createElement("name");
        name.setTextContent(employee.getName());
        Element surname = doc.createElement("surname");
        surname.setTextContent(employee.getSurname());
        Element yearOfBirth = doc.createElement("yearOfBirth");
        yearOfBirth.setTextContent(employee.getYearOfBirth() + "");
        Element phone = doc.createElement("phone");
        phone.setTextContent(employee.getPhoneNumber());
        Element type = doc.createElement("type");
        type.setTextContent(employee.getClass().getSimpleName());
        Element managerName = doc.createElement("managerName");
        managerName.setTextContent(employee.getManagerName());
        person.appendChild(type);
        person.appendChild(name);
        person.appendChild(surname);
        person.appendChild(yearOfBirth);
        person.appendChild(phone);
        person.appendChild(managerName);
        return person;
    }

}
