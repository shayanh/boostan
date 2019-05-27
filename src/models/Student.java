package models;

import respository.RepositoryContainer;

import java.io.InvalidObjectException;
import java.util.ArrayList;

public class Student extends Entity {
    private String studentID;
    private Person person;
    private Major major;
    private ArrayList<StudentSemester> studentSemesters = new ArrayList<>();
    private Curriculum curriculum;

    Student(int id) {
        super(id);
    }

    public boolean hasPassedCourse(Course course) {
        for (StudentSemester studentSemester: studentSemesters) {
            if (studentSemester.isCoursePassed(course)) {
                return true;
            }
        }
        return false;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public StudentSemester getLastFinishedSemester() {
        StudentSemester result = null;
        for (StudentSemester studentSemester: studentSemesters) {
            if (studentSemester.getSemester().isFinished())
                result = studentSemester;
        }
        return result;
    }

    public StudentSemester getCurrentSemester() throws IllegalStateException {
        for (StudentSemester studentSemester: studentSemesters) {
            if (!studentSemester.getSemester().isFinished()) {
                return studentSemester;
            }
        }
        throw new IllegalStateException("No open semester found for this student");
    }

    public int getPassedCredits() {
        int result = 0;
        for (StudentSemester studentSemester: studentSemesters) {
            result += studentSemester.getPassedCredits();
        }
        return result;
    }

    public void enroll(CourseOffering offering) throws InvalidObjectException {
        CurriculumRow row = this.getCurriculum().getCorrespondingRow(offering.getCourse());
        Enrollment enrollment = new Enrollment(offering, row);
        enrollment.setState(EnrollmentState.REGISTERED);
        RepositoryContainer.studentRepository.insertEnrollment(this.getCurrentSemester(), enrollment);
        RepositoryContainer.semesterRepository.addEnrollment(offering, enrollment);
    }

    public void unenroll(CourseOffering offering) throws InvalidObjectException {
        StudentSemester studentSemester = this.getCurrentSemester();
        Enrollment enrollment = studentSemester.getEnrollment(offering);
        RepositoryContainer.studentRepository.removeEnrollment(studentSemester, enrollment);
        RepositoryContainer.semesterRepository.removeEnrollment(offering, enrollment);
        // TODO
    }

    public void addToWaitingList(CourseOffering offering) {
        // TODO
    }
}
