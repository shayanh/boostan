package services;

import models.*;
import respositories.RepositoryContainer;

import java.io.InvalidObjectException;
import java.util.ArrayList;

public class CourseSelectionService {
    public ArrayList<CourseOffering> getOfferingsFor(Student student) {
        Curriculum curriculum = student.getCurriculum();
        Semester semester = RepositoryContainer.semesterRepository.getCurrentSemester();
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

    public boolean selectOfferingsFor(Student student, ArrayList<CourseOfferingRequest> requests) {
        StudentSemester studentSemester = student.getCurrentSemester();
        RegistrationValidation registrationValidation = studentSemester.getRegistrationValidation();

        ArrayList<CourseOffering> enrollOfferings = new ArrayList<>();
        ArrayList<CourseOffering> waitingOfferings = new ArrayList<>();
        for (CourseOfferingRequest request: requests) {
            if (request.getAction() == CourseOfferingAction.ENROLL || request.getAction() == CourseOfferingAction.NONE) {
                enrollOfferings.add(request.getCourseOffering());
            }
            if (request.getAction() == CourseOfferingAction.WAITING) {
                waitingOfferings.add(request.getCourseOffering());
            }
        }
        boolean isValid = registrationValidation.validate(enrollOfferings, waitingOfferings);
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

    public boolean withdrawEnrollmentFor(Student student, Enrollment enrollment) {
        StudentSemester studentSemester = student.getCurrentSemester();
        WithdrawValidation withdrawValidation = studentSemester.getWithdrawValidation();
        if (!withdrawValidation.validate(enrollment)) {
            System.out.println(withdrawValidation.getErrorMessage());
            return false;
        }

        try {
            student.unenroll(enrollment.getCourseOffering());
        } catch (InvalidObjectException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
