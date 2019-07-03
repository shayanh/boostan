package models;

import java.util.HashMap;

public class Course extends Entity {
    private String name;
    private boolean isInternship;
    private HashMap<CreditType, Float> credits;

    public Course(HashMap<CreditType, Float> credits, String name, boolean isInternship) {
        this.credits = credits;
        this.name = name;
        this.isInternship = isInternship;
    }

    public String getName() {
        return name;
    }

    public boolean isInternship() {
        return isInternship;
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
