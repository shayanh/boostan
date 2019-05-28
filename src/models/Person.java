package models;

public class Person extends Entity {
    private String firstName;
    private String lastName;
    private String ssn;

    public Person(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }
}
