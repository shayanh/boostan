package models;

import java.util.ArrayList;

public class CourseCorequisite extends Prerequisite {
    private Course corequisiteCourse;

    public CourseCorequisite(Course targetCourse, Course corequisiteCourse) {
        super(targetCourse);
        this.corequisiteCourse = corequisiteCourse;
    }

    @Override
    public boolean isSatisfied(Student student) {
        return true;
    }

    @Override
    public boolean isSatisfied(Student student, ArrayList<CourseOffering> currentTermOfferings) {
        for (CourseOffering offering : currentTermOfferings)
            if (offering.getCourse().equals(corequisiteCourse))
                return true;
        return student.hasPassedCourse(corequisiteCourse);
    }
}
