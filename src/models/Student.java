package models;

import java.util.ArrayList;

public class Student extends Entity {
    private String studentID;
    private Person person;
    private Major major;
    private ArrayList<StudentSemester> studentSemesters = new ArrayList<>();
    private Curriculum curriculum;

    Student(int id) {
        super(id);
    }

    public boolean hasPassedCourse(Course course) {
        for (StudentSemester studentSemester: studentSemesters) {
            if (studentSemester.isCoursePassed(course)) {
                return true;
            }
        }
        return false;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public int getPassedCredits() {
        int result = 0;
        for (StudentSemester studentSemester: studentSemesters) {
            result += studentSemester.getPassedCredits();
        }
        return result;
    }
}
