package models;

public class SpecificCourse extends CurriculumRow {
    private Course course;

    public SpecificCourse( Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}
