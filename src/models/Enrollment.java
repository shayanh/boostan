package models;

import java.util.Map;

public class Enrollment extends Entity {
    private float grade;
    private EnrollmentState state;
    private CourseOffering courseOffering;
    private CurriculumRow curriculumRow;

    public Enrollment() {}

    public Enrollment(int id) {
        super(id);
    }

    public Enrollment(CourseOffering courseOffering, CurriculumRow curriculumRow) {
        this.courseOffering = courseOffering;
        this.curriculumRow = curriculumRow;
    }

    public float getGrade() {
        return grade;
    }

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public EnrollmentState getState() {
        return state;
    }

    public CurriculumRow getCurriculumRow() {
        return curriculumRow;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public void setState(EnrollmentState state) {
        this.state = state;
    }

    public boolean canWEliminate() {
        Map<CreditType, Float> credits = this.curriculumRow.getCredits();
        return credits.get(CreditType.PRACTICE) != null && credits.get(CreditType.PRACTICE) > 0;
    }
}
