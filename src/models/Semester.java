package models;

public class Semester extends Entity {
    private DateTimePeriod registerationPeriod;
    private DateTimePeriod editingPeriod;
    private DateTimePeriod eliminationPeriod;
    private int year;
    private SemesterTime time;

    public Semester(int id) {
        super(id);
    }
}
