package edu.ccrm.model;

/**
 * Represents an instructor, inheriting from Person.
 */
public class Instructor extends Person {

    private String department;

    public Instructor(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getDetails() {
        return "Instructor: " + name + ", Dept: " + department;
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
