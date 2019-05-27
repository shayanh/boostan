package models;

import java.util.ArrayList;

public class CourseOffering {
    private int capacity;
    private boolean offeredOutside;
    private ArrayList<ExamSession> examSessions;
    private Faculty faculty;
    private ArrayList<Session> sessions;
    private ArrayList<Professor> lecturers;
    private Course course;
    ArrayList<Enrollment> enrollments;
    private Semester semester;

    public Course getCourse() {
        return course;
    }

    public boolean hasCapacity() {
        return enrollments.size() < capacity;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public ArrayList<ExamSession> getExamSessions() {
        return examSessions;
    }
}
