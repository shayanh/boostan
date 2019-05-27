package models;

public class Enrollment extends Entity {
    private int grade;
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

    public int getGrade() {
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

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setState(EnrollmentState state) {
        this.state = state;
    }
}
