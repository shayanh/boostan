package models;

public class CoursePrerequisite extends Prerequisite {
    private Course prerequisiteCourse;

    @Override
    boolean isSatisfied(Student student) {
        return student.hasPassedCourse(prerequisiteCourse);
    }
}
