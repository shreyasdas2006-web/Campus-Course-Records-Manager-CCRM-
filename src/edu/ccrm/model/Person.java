package edu.ccrm.model;

/**
 * An abstract base class for any person in the system (e.g., Student,
 * Instructor).
 */
public abstract class Person {

    protected int id;
    protected String name;
    protected String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * An abstract method to get a detailed string representation of the person.
     * Must be implemented by subclasses.
     * 
     * @return A detailed string.
     */
    public abstract String getDetails();
}
