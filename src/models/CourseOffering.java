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
}