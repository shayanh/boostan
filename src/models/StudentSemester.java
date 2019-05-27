package models;

import java.util.ArrayList;
import java.util.Date;

public class StudentSemester extends Entity {
    private Date registerStartTime;
    private Date editingStartTime;
    private ArrayList<Enrollment> enrollments = new ArrayList<>();
    private Semester semester;

    public StudentSemester(int id) {
        super(id);
    }

    public Semester getSemester() {
        return semester;
    }

    public boolean isCoursePassed(Course course) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseOffering().getCourse().equals(course)
                    && enrollment.getState() == EnrollmentState.PASSED) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public int getPassedCredits() {
        int result = 0;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getState() == EnrollmentState.PASSED) {
                result += enrollment.getCurriculumRow().getCreditsSum();
            }
        }
        return result;
    }

    public float getGPA() throws IllegalStateException{
        int sumCredits = 0;
        float sum = 0;
        for (Enrollment enrollment : enrollments) {
            EnrollmentState state = enrollment.getState();
            if (state == EnrollmentState.PASSED ||
                    state == EnrollmentState.FAILED ||
                    state == EnrollmentState.GRADE_VERIFIED) {
                int credits = enrollment.getCurriculumRow().getCreditsSum();
                sum += enrollment.getGrade() * credits;
                sumCredits += credits;
            }
        }
        if (sumCredits == 0)
            throw new IllegalStateException();
        return sum / sumCredits;
    }

}
