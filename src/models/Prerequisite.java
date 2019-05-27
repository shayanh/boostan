package models;

public abstract class Prerequisite {
    private Course targetCourse;

    public Course getTargetCourse() {
        return targetCourse;
    }

    abstract boolean isSatisfied(Student student);
}
