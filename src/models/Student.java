package models;

public class Student extends Entity {
    private String studentID;
    private Person person;
    private Major major;

    Student(int id) {
        super(id);
    }
}
