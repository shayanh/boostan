package models;

import java.util.ArrayList;

public abstract class RegistrationValidation {
    protected Student student;
    protected ArrayList<CourseOffering> offerings;
    protected ArrayList<CourseOffering> waitingOfferings;
    protected ArrayList<CourseOffering> enrollOfferings;
    protected String errorMessage;

    public RegistrationValidation(Student student) {
        this.student = student;
        enrollOfferings = new ArrayList<>();
        waitingOfferings = new ArrayList<>();
        offerings = new ArrayList<>();
    }

    public boolean validate(ArrayList<CourseOffering> enrollOfferings, ArrayList<CourseOffering> waitingOfferings) {
        this.offerings = new ArrayList<>();
        offerings.addAll(enrollOfferings);
        offerings.addAll(waitingOfferings);

        return checkCapacity() && checkPrerequisites() && checkTimeOverlap()
                && checkDuplicateCourse() && checkInternship() && checkMinMaxCredit() && checkWaitingList();
    }

    protected int getOfferingsCreditSum() throws IllegalArgumentException {
        int credits = 0;
        Curriculum curriculum = student.getCurriculum();
        for (CourseOffering offering : offerings) {
            Course course = offering.getCourse();
            CurriculumRow row = curriculum.getCorrespondingRow(course);
            credits += row.getCreditsSum();
        }
        return credits;
    }

    protected boolean checkCapacity() {
        for (CourseOffering offering : enrollOfferings) {
            if (!offering.hasCapacity()) {
                errorMessage = "capacity exceeded";
                return false;
            }
        }
        return true;
    }

    protected boolean checkPrerequisites() {
        Curriculum curriculum = student.getCurriculum();
        for (CourseOffering offering : offerings) {
            Course course = offering.getCourse();
            if (!curriculum.isPrerequisiteSatisfied(student, course, offerings)) {
                errorMessage = "requisite not satisfied";
                return false;
            }
        }
        return true;
    }

    protected boolean checkTimeOverlap() {
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

    protected boolean checkDuplicateCourse() {
        for (CourseOffering offering : offerings)
            if (student.hasPassedCourse(offering.getCourse())) {
                errorMessage = "course is already passed";
                return false;
            }
        return true;
    }

    protected boolean checkInternship() {
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

    protected boolean checkWaitingList() {
        for (CourseOffering offering: waitingOfferings) {
            if (!offering.hasWaitingListCapacity() || offering.isInWaitingList(student)) {
                errorMessage = "error in adding to waiting list";
                return false;
            }
        }
        return true;
    }

    abstract boolean checkMinMaxCredit();

    public String getErrorMessage() {
        return errorMessage;
    }
}
