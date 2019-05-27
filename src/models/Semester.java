package models;

import java.util.ArrayList;

public class Semester extends Entity {
    private DateTimePeriod registerationPeriod;
    private DateTimePeriod editingPeriod;
    private DateTimePeriod eliminationPeriod;
    private int year;
    private SemesterTime time;
    ArrayList<CourseOffering> courseOfferings = new ArrayList<>();
    private boolean finished;

    public Semester(int id) {
        super(id);
    }

    public boolean isFinished() {
        return finished;
    }

    public ArrayList<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }
}
