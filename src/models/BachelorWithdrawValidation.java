package models;

public class BachelorWithdrawValidation extends WithdrawValidation {
    @Override
    boolean checkMinCredit() {
        errorMessage = "min credit error";
        return this.getEnrollmentsCreditSum() >= 12;
    }
}
