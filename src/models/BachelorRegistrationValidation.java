package models;

public class BachelorRegistrationValidation extends RegistrationValidation {
    private static final float GPA_THRESHOLD = 17;

    public BachelorRegistrationValidation(Student student) {
        super(student);
    }

    @Override
    boolean checkMinMaxCredit() {
        try {
            int credits = getOfferingsCreditSum();
            StudentSemester studentSemester = student.getLastFinishedSemester();
            float previousGPA = studentSemester.getGPA();
            if (previousGPA >= GPA_THRESHOLD)
                return credits <= 24 && credits >= 12;
            else
                return credits <= 18 && credits >= 12;
        } catch (IllegalArgumentException e) {
            errorMessage = "course not in curriculum";
            return false;
        } catch (IllegalStateException e) {
            errorMessage = "last term is not finished yet";
            return false;
        }
    }
}
