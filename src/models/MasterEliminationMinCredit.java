package models;

public class MasterEliminationMinCredit implements EliminationMinCreditStrategy {
    @Override
    public int getMinValidCredit() {
        return 0;
    }
}
