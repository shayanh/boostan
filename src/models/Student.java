package models;

import java.util.ArrayList;

public class Student extends Entity {
    private String studentID;
    private Person person;
    private Major major;
    private ArrayList<StudentSemester> studentSemesters;
    private Curriculum curriculum;

    Student(int id) {
        super(id);
    }
}
