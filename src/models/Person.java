package models;

public class Person extends Entity {
    private String firstName;
    private String lastName;
    private String ssn;

    public Person(int id) {
        super(id);
    }
}
