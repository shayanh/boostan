package services;

import models.*;
import respository.SemesterRepository;

import java.util.ArrayList;

public class CourseSelection {
    private SemesterRepository semesterRepository;

    CourseSelection() {
        semesterRepository = new SemesterRepository();
    }

    public ArrayList<CourseOffering> getOfferings(Student student) {
        Curriculum curriculum = student.getCurriculum();
        Semester semester = semesterRepository.getCurrentSemester();
        ArrayList<CourseOffering> allOfferings = semester.getCourseOfferings();
        ArrayList<CourseOffering> result = new ArrayList<>();
        for (CourseOffering offering: allOfferings) {
            Course course = offering.getCourse();
            if (curriculum.hasCourse(course)
                    && !student.hasPassedCourse(course)
                    && curriculum.isPrerequisiteSatisfied(student, course)) {
                result.add(offering);
            }
        }
        return result;
    }

    public boolean selectOfferings(Student student, ArrayList<CourseOffering> offerings) {
        return false;
    }
}
