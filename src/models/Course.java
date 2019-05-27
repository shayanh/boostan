package models;

public class Course extends Entity {
    private String name;
    private boolean isInternship;

    public Course(int id, String name, boolean isInternship) {
        super(id);
        this.name = name;
        this.isInternship = isInternship;
    }

    public boolean isInternship() {
        return isInternship;
    }

    public Course(int id) {
        super(id);
    }
}
