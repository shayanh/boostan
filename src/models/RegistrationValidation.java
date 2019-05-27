package models;

import java.util.ArrayList;

public abstract class RegistrationValidation {
    protected Student student;
    protected ArrayList<CourseOffering> offerings;
    protected String errorMessage;

    public RegistrationValidation(Student student) {
        this.student = student;
    }

    boolean validate(ArrayList<CourseOffering> offerings) {
        this.offerings = offerings;
        return checkCapacity() && checkPrerequisites();
    }

    protected int getOfferingCreditSum() throws IllegalArgumentException {
        int credits = 0;
        for (CourseOffering offering : offerings) {
            Course course = offering.getCourse();
            Curriculum curriculum = student.getCurriculum();
            CurriculumRow row = curriculum.getCorrespondingRow(course);
            credits += row.getCreditsSum();
        }
        return credits;
    }

    boolean checkCapacity() {
        for (CourseOffering offering : offerings) {
            if (!offering.hasCapacity()) {
                errorMessage = "capacity exceeded";
                return false;
            }
        }
        return true;
    }

    boolean checkPrerequisites() {
        Curriculum curriculum = student.getCurriculum();
        for (CourseOffering offering : offerings) {
            Course course = offering.getCourse();
            if (!curriculum.isPrerequisiteSatisfied(student, course)) {
                errorMessage = "requisite not satisfied";
                return false;
            }
        }
        return true;
    }

    boolean checkTimeOverlap() {
        for (CourseOffering offering : offerings) {
            for (CourseOffering offering1 : offerings) {
                for (ExamSession session : offering.getExamSessions()) {
                    for (ExamSession session1 : offering1.getExamSessions())
                        if (session.hasConflict(session1)) {
                            errorMessage = "exam time overlap";
                            return false;
                        }
                }
                for (Session session : offering.getSessions()) {
                    for (Session session1 : offering1.getSessions())
                        if (session.hasConflict(session1)) {
                            errorMessage = "class session time overlap";
                            return false;
                        }
                }
            }
        }
        return true;
    }

    boolean checkRepeatitiveCourse() {
        for (CourseOffering offering : offerings)
            if (student.hasPassedCourse(offering.getCourse())) {
                errorMessage = "course is already passed";
                return false;
            }
        return true;
    }

    boolean checkInternship() {
        boolean hasInternship = false;
        for (CourseOffering offering : offerings) {
            if (offering.getCourse().isInternship())
                hasInternship = true;
        }

        if (hasInternship) {
            return offerings.size() == 1;
        }
        return true;
    }

    abstract boolean minMaxCredit();

}
