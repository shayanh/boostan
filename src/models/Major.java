package models;

public class Major extends Entity {
    private String name;
    private Integer degreeID;
    private Integer facultyID;

    public Major(String name, Integer degreeID, Integer facultyID) {
        this.name = name;
        this.degreeID = degreeID;
        this.facultyID = facultyID;
    }
}
