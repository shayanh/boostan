package models;

public class Enrollment extends Entity {
    private int grade;
    private EnrollmentState state;
    private CourseOffering courseOffering;
    private CurriculumRow curriculumRow;

    public Enrollment(int id) {
        super(id);
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
}
