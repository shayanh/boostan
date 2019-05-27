package models;

import java.util.ArrayList;

public abstract class EliminationValidation {
    protected Student student;
    protected ArrayList<Enrollment> enrollments;
    protected String errorMessage;

    abstract boolean checkMinCredit();

    protected int getEnrollmentsCreditSum() {
        int credits = 0;
        Curriculum curriculum = student.getCurriculum();
        for (Enrollment enrollment: enrollments) {
            Course course = enrollment.getCourseOffering().getCourse();
            CurriculumRow row = curriculum.getCorrespondingRow(course);
            credits += row.getCreditsSum();
        }
        return credits;
    }

    public boolean validate(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
        return this.checkMinCredit();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
