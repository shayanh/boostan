package models;

import java.util.HashMap;

public abstract class CurriculumRow {
    private HashMap<CreditType, Float> credits;


    public CurriculumRow(HashMap<CreditType, Float> credits) {
        this.credits = credits;
    }


    public HashMap<CreditType, Float> getCredits() {
        return credits;
    }

    public int getCreditsSum() {
        float result = 0;
        for (Float f : credits.values()) {
            result += f;
        }
        return (int) result;
    }
}
