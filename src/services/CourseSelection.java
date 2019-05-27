package services;

import models.*;
import respository.SemesterRepository;

import java.io.InvalidObjectException;
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

    public boolean selectOfferings(Student student, ArrayList<CourseOfferingRequest> requests) {
        StudentSemester studentSemester = student.getCurrentSemester();
        RegistrationValidation registrationValidation = studentSemester.getRegistrationValidation();
        ArrayList<CourseOffering> offerings = new ArrayList<>();
        for (CourseOfferingRequest request: requests) {
            if (request.action == CourseOfferingAction.ENROLL || request.action == CourseOfferingAction.NONE) {
                offerings.add(request.getCourseOffering());
            }
        }
        boolean isValid = registrationValidation.validate(offerings);
        if (!isValid) {
            System.out.println(registrationValidation.getErrorMessage());
            return false;
        }
        for (CourseOfferingRequest request: requests) {
            CourseOffering offering = request.getCourseOffering();
            if (request.getAction() == CourseOfferingAction.DELETE) {
                try {
                    student.unenroll(offering);
                } catch (InvalidObjectException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (request.getAction() == CourseOfferingAction.ENROLL) {
                try {
                    student.enroll(offering);
                } catch (InvalidObjectException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (request.getAction() == CourseOfferingAction.WAITING) {
                student.addToWaitingList(offering);
            }
        }
        return true;
    }
}
