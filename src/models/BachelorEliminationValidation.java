package models;

public class BachelorEliminationValidation extends EliminationValidation {
    @Override
    boolean checkMinCredit() {
        errorMessage = "min credit error";
        return this.getEnrollmentsCreditSum() >= 12;
    }
}
