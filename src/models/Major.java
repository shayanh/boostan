package models;

public class Major extends Entity {
    private String name;
    private Degree degree;
    private Faculty faculty;

    public Major(int id) {
        super(id);
    }
}
