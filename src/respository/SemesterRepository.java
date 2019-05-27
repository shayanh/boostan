package respository;

import models.CourseOffering;
import models.Enrollment;
import models.Semester;

public class SemesterRepository extends Repository<Semester> {
    Repository<CourseOffering> courseOfferings;

    public SemesterRepository() {
        super();
        courseOfferings = new Repository<>();
    }

    public Semester getCurrentSemester() {
        for (Semester semester: records) {
            if (semester.isFinished()) {
                return semester;
            }
        }
        throw new IllegalStateException("No open semester found");
    }

    public void addEnrollment(CourseOffering offering, Enrollment enrollment) {
        offering.addEnrollment(enrollment);
    }

    public void removeEnrollment(CourseOffering offering, Enrollment enrollment) {
        offering.removeEnrollment(enrollment);
    }
}
