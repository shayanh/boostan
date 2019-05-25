package models;

public class BachelorEliminationMinCredit implements EliminationMinCreditStrategy {
    @Override
    public int getMinValidCredit() {
        return 0;
    }
}
