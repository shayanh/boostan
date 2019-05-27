package models;

public class MinCreditPrerequisite extends Prerequisite {
    private int minCredit;

    @Override
    boolean isSatisfied(Student student) {
        return student.getPassedCredits() >= minCredit;
    }
}
