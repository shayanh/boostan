package models;

import respositories.RepositoryContainer;

import java.io.InvalidObjectException;
import java.util.ArrayList;

public class Student extends Entity {
    private String studentID;
    private Person person;
    private Integer majorID;
    private ArrayList<StudentSemester> studentSemesters = new ArrayList<>();
    private Integer curriculumID;

    public Student(int id) {
        super(id);
    }

    public Student(String studentID, Person person, Integer majorID, Integer curriculumID) {
        this.studentID = studentID;
        this.person = person;
        this.majorID = majorID;
        this.curriculumID = curriculumID;
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
        return RepositoryContainer.curriculumRepository.find(curriculumID);
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
        this.getCurrentSemester().addEnrollment(enrollment);
        RepositoryContainer.semesterRepository.addEnrollment(offering, enrollment);
    }

    public void unenroll(CourseOffering offering) throws InvalidObjectException {
        StudentSemester studentSemester = this.getCurrentSemester();
        Enrollment enrollment = studentSemester.getEnrollment(offering);
        RepositoryContainer.semesterRepository.removeEnrollment(studentSemester, enrollment, offering);
    }

    public StudentSemester createSemester() throws InvalidObjectException {
        StudentSemester studentSemester = new StudentSemester(this);
        RepositoryContainer.studentRepository.insertStudentSemester(studentSemester);
        studentSemesters.add(studentSemester);
        return studentSemester;
    }

    public void addToWaitingList(CourseOffering offering) {
        offering.addToWaitingList(this);
    }
}
