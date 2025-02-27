package models;

import java.util.ArrayList;

public class Semester extends Entity {
    private DateTimePeriod registerationPeriod;
    private DateTimePeriod editingPeriod;
    private DateTimePeriod withdrawPeriod;
    private int year;
    private SemesterTime time;
    ArrayList<CourseOffering> courseOfferings = new ArrayList<>();
    private boolean finished;

    public Semester(ArrayList<CourseOffering> courseOfferings) {
        this.courseOfferings = courseOfferings;
        finished = false;
    }


    public boolean isFinished() {
        return finished;
    }

    public ArrayList<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }
}
