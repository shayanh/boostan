package models;

public class Course extends Entity {
    private String name;
    private boolean isInternship;

    public Course(String name, boolean isInternship) {
        this.name = name;
        this.isInternship = isInternship;
    }

    public String getName() {
        return name;
    }

    public boolean isInternship() {
        return isInternship;
    }

}
