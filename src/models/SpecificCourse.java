package models;

import java.util.HashMap;

public class SpecificCourse extends CurriculumRow {
    private Course course;

    public SpecificCourse(HashMap<CreditType, Float> credits, Course course) {
        super(credits);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}
