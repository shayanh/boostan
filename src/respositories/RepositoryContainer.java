package respositories;

public class RepositoryContainer {
    public final static CourseRepository courseRepository = new CourseRepository();
    public final static CurriculumRepository curriculumRepository = new CurriculumRepository();;
    public final static DegreeRepository degreeRepository = new DegreeRepository();;
    public final static FacultyRepository facultyRepository = new FacultyRepository();;
    public final static MajorRepository majorRepository = new MajorRepository();;
    public final static ProfessorRepository professorRepository = new ProfessorRepository();;
    public final static SemesterRepository semesterRepository = new SemesterRepository();;
    public final static StudentRepository studentRepository = new StudentRepository();;
    public final static CourseOfferingRepository courseOfferingRepository = new CourseOfferingRepository();
    public final static EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
}
