package models;

import java.util.ArrayList;

public abstract class Prerequisite {
    protected Course targetCourse;

    public Prerequisite(Course targetCourse) {
        this.targetCourse = targetCourse;
    }

    public Course getTargetCourse() {
        return targetCourse;
    }

    abstract public boolean isSatisfied(Student student);

    public boolean isSatisfied(Student student, ArrayList<CourseOffering> offerings) {
        return isSatisfied(student);
    }
}
