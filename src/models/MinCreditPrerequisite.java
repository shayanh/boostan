package models;

public class MinCreditPrerequisite extends Prerequisite {
    private int minCredit;

    public MinCreditPrerequisite(Course targetCourse, int minCredit) {
        super(targetCourse);
        this.minCredit = minCredit;
    }

    @Override
    public boolean isSatisfied(Student student) {
        return student.getPassedCredits() >= minCredit;
    }

}
