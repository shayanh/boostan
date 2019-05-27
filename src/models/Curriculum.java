package models;

import java.util.ArrayList;

public class Curriculum extends Entity {
    private Major major;
    private ArrayList<CurriculumBox> boxes;
    private ArrayList<Prerequisite> prerequisites = new ArrayList<>();
    private Semester semester;

    public Curriculum(int id) {
        super(id);
    }

    public boolean hasCourse(Course course) {
        for (CurriculumBox box: boxes) {
            if (box.hasCourse(course)) {
                return true;
            }
        }
        return false;
    }

    public CurriculumRow getCorrespondingRow(Course course) throws IllegalArgumentException{
        for (CurriculumBox box: boxes) {
            CurriculumRow row = box.getCorrespondingRow(course);
            if (row != null)
                return row;
        }
        throw new IllegalArgumentException("Course not Found in Curriculum");
    }

    public boolean isPrerequisiteSatisfied(Student student, Course course) {
        for (Prerequisite prerequisite: prerequisites) {
            if (prerequisite.getTargetCourse().equals(course) && !prerequisite.isSatisfied(student)) {
                return false;
            }
        }
        return true;
    }
}
