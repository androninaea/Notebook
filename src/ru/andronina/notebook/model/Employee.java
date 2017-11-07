package ru.andronina.notebook.model;

public class Employee extends Person {
    public static final Employee EMPTY = new Employee();

    private String managerName;

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return
                "id: " + super.getId() +
                        "\n" + super.getName() +
                        "\n" + super.getSurname() +
                        "\n" + super.getYearOfBirth() +
                        "\n" + super.getPhoneNumber() +
                        "\n" + managerName + "\n";
    }
}
