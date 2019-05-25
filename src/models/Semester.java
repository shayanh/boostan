package models;

public class Semester extends Entity {
    private DateTimePeriod registerationPeriod;
    private DateTimePeriod editingPeriod;
    private DateTimePeriod eliminationPeriod;


    public Semester(int id) {
        super(id);
    }
}
