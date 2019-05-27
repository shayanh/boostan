package models;

public class CourseCorequisite extends Prerequisite {
    private Course corequisiteCourse;

    @Override
    boolean isSatisfied(Student student) {
        return true;
    }
}
