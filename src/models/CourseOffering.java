package models;

import java.io.InvalidObjectException;
import java.util.ArrayList;

public class CourseOffering extends Entity {
    private int capacity; //
    private boolean offeredOutside;
    private ArrayList<ExamSession> examSessions; //
    private Faculty faculty;
    private ArrayList<Session> sessions; //
    private ArrayList<Professor> lecturers;
    private Course course; //

    private ArrayList<Enrollment> enrollments = new ArrayList<>();
    private Semester semester;
    private ArrayList<Student> waitingList = new ArrayList<>();
    private static int WAITING_LIST_CAPACITY = 10;


    public CourseOffering(int capacity, ArrayList<ExamSession> examSessions, ArrayList<Session> sessions, Course course) {
        this.capacity = capacity;
        this.examSessions = examSessions;
        this.sessions = sessions;
        this.course = course;
    }

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

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) throws InvalidObjectException {
        enrollments.remove(enrollment);
        if (!waitingList.isEmpty()) {
            Student student = waitingList.get(0);
            waitingList.remove(0);
            student.enroll(this);
        }
    }

    public void addToWaitingList(Student newStudent) {
        waitingList.add(newStudent);
    }

    public void removeFromWaitingList(Student student) {
        waitingList.remove(student);
    }

    public boolean hasWaitingListCapacity() {
        return waitingList.size() < WAITING_LIST_CAPACITY;
    }

    public boolean isInWaitingList(Student newStudent) {
        for (Student student : waitingList) {
            if (student.equals(newStudent)) {
                return true;
            }
        }
        return false;
    }
}
