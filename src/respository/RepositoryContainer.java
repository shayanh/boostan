package respository;

public class RepositoryContainer {
    private static RepositoryContainer ourInstance = new RepositoryContainer();

    public CourseRepository courseRepository;
    public CurriculumRepository curriculumRepository;
    public DegreeRepository degreeRepository;
    public FacultyRepository facultyRepository;
    public MajorRepository majorRepository;
    public ProfessorRepository professorRepository;
    public SemesterRepository semesterRepository;
    public StudentRepository studentRepository;

    public static RepositoryContainer getInstance() {
        return ourInstance;
    }

    private RepositoryContainer() {
        courseRepository = new CourseRepository();
        curriculumRepository = new CurriculumRepository();
        degreeRepository = new DegreeRepository();
        facultyRepository = new FacultyRepository();
        majorRepository = new MajorRepository();
        professorRepository = new ProfessorRepository();
        semesterRepository = new SemesterRepository();
        studentRepository = new StudentRepository();
    }
}
