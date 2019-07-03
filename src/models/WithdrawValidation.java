package models;

import java.util.ArrayList;

public abstract class WithdrawValidation {
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

    public boolean validate(Enrollment withdrawnEnrollment) {
        StudentSemester studentSemester = student.getCurrentSemester();
        if (studentSemester.hasWithdrawn() || !withdrawnEnrollment.canWithdraw()) {
            return false;
        }
        ArrayList<Enrollment> tempEnrollments = studentSemester.getEnrollments();
        tempEnrollments.remove(withdrawnEnrollment);
        this.enrollments = tempEnrollments;
        return this.checkMinCredit();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
