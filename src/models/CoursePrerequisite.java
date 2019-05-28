package models;

public class CoursePrerequisite extends Prerequisite {
    private Course prerequisiteCourse;

    public CoursePrerequisite(Course targetCourse, Course prerequisiteCourse) {
        super(targetCourse);
        this.prerequisiteCourse = prerequisiteCourse;
    }

    @Override
    public boolean isSatisfied(Student student) {
        return student.hasPassedCourse(prerequisiteCourse);
    }
}
