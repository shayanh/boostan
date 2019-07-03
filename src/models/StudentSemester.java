package models;

import respositories.RepositoryContainer;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Date;

public class StudentSemester extends Entity {
    private Date registerStartTime;
    private Date editingStartTime;
    private ArrayList<Enrollment> enrollments = new ArrayList<>();
    private Integer semesterID;
    private SemesterState semesterState;
    private RegistrationValidation registrationValidation;
    private EliminationValidation eliminationValidation;
    private boolean wEliminated;

    public StudentSemester(Student student) {
        semesterID = RepositoryContainer.semesterRepository.getCurrentSemester().getId();
        registrationValidation = new BachelorRegistrationValidation(student);
        eliminationValidation = new BachelorEliminationValidation();
        wEliminated = false;
    }

    public Semester getSemester() {
        return RepositoryContainer.semesterRepository.find(semesterID);
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

    public float getGPA() throws IllegalStateException {
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

    public RegistrationValidation getRegistrationValidation() {
        return registrationValidation;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
    }

    public Enrollment getEnrollment(CourseOffering offering) throws InvalidObjectException {
        for (Enrollment enrollment: enrollments) {
            if (enrollment.getCourseOffering().equals(offering)) {
                return enrollment;
            }
        }
        throw new InvalidObjectException("There no such enrollment with specified offering");
    }

    public boolean iswEliminated() {
        return wEliminated;
    }

    public EliminationValidation getEliminationValidation() {
        return eliminationValidation;
    }
}
