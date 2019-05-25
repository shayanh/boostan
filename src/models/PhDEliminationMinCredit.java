package models;

public class PhDEliminationMinCredit implements EliminationMinCreditStrategy {
    @Override
    public int getMinValidCredit() {
        return 0;
    }
}
