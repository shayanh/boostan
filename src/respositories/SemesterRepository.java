package respositories;

import models.CourseOffering;
import models.Enrollment;
import models.Semester;
import models.StudentSemester;

import java.io.InvalidObjectException;

public class SemesterRepository extends Repository<Semester> {
    private Repository<Enrollment> enrollments;
    private Repository<CourseOffering> courseOfferings;

    public SemesterRepository() {
        super();
        courseOfferings = new Repository<>();
        enrollments = new Repository<>();
    }

    public void addOffering(CourseOffering offering) throws InvalidObjectException {
        courseOfferings.insert(offering);
    }

    public Semester getCurrentSemester() {
        for (Semester semester: records) {
            if (!semester.isFinished()) {
                return semester;
            }
        }
        throw new IllegalStateException("No open semester found");
    }

    public void addEnrollment(CourseOffering offering, Enrollment enrollment) throws InvalidObjectException {
        enrollments.insert(enrollment);
        offering.addEnrollment(enrollment);
    }

    public void removeEnrollment(StudentSemester studentSemester, Enrollment enrollment, CourseOffering offering) throws InvalidObjectException {
        studentSemester.removeEnrollment(enrollment);
        enrollments.remove(enrollment);
        offering.removeEnrollment(enrollment);
    }

}
