package respositories;

import models.Semester;

public class SemesterRepository extends Repository<Semester> {
    public SemesterRepository() {
        super();
    }

    public Semester getCurrentSemester() {
        for (Semester semester: records) {
            if (!semester.isFinished()) {
                return semester;
            }
        }
        throw new IllegalStateException("No open semester found");
    }
}
