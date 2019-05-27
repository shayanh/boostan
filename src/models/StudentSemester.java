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

    public boolean isCoursePassed(Course course) {
        for (Enrollment enrollment: enrollments) {
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
        for (Enrollment enrollment: enrollments) {
            if (enrollment.getState() == EnrollmentState.PASSED) {
                result += enrollment.getCurriculumRow().getCreditsSum();
            }
        }
        return result;
    }
}
