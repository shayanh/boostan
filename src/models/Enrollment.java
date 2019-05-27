package models;

public class Enrollment extends Entity {
    private int grade;
    private EnrollmentState state;
    private CourseOffering courseOffering;
    private CurriculumRow curriculumRow;

    public Enrollment(int id) {
        super(id);
    }
}
