package models;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException(); // TODO
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
