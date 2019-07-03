package respositories;

import models.Student;
import models.StudentSemester;

import java.io.InvalidObjectException;

public class StudentRepository extends Repository<Student> {
    private Repository<StudentSemester> studentSemesters;

    StudentRepository() {
        super();
        studentSemesters = new Repository<>();
    }

    public void insertStudentSemester(StudentSemester studentSemester) throws InvalidObjectException {
        studentSemesters.insert(studentSemester);
    }

}
