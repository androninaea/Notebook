package ru.andronina.notebook.model;

public class Manager extends Person {
    public static final Manager EMPTY = new Manager();

    private String name = super.getName();
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }


    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return
                "id: " + super.getId() +
                        "\n" + super.getName() +
                        "\n" + super.getSurname() +
                        "\n" + super.getYearOfBirth() +
                        "\n" + super.getPhoneNumber() +
                        "\n" + departmentName + "\n";
    }
}
