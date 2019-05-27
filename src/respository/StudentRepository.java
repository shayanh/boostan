package respository;

import models.Enrollment;
import models.Student;
import models.StudentSemester;

import java.io.InvalidObjectException;

public class StudentRepository extends Repository<Student> {
    private Repository<Enrollment> enrollments;
    private Repository<StudentSemester> studentSemesters;
    private int nextEnrollmentID;

    StudentRepository() {
        super();
        enrollments = new Repository<>();
        studentSemesters = new Repository<>();
        nextEnrollmentID = 0;
    }

    private int nextEnrollmentID() {
        return nextEnrollmentID++;
    }

    public void insertEnrollment(StudentSemester studentSemester, Enrollment enrollment) throws InvalidObjectException {
        enrollments.insert(enrollment);
        studentSemester.addEnrollment(enrollment);
    }

    public void removeEnrollment(StudentSemester studentSemester, Enrollment enrollment) {
        studentSemester.removeEnrollment(enrollment);
        enrollments.remove(enrollment);
    }
}
