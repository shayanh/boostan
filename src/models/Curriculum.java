package models;

import java.util.ArrayList;

public class Curriculum extends Entity {
    private Major major;
    private ArrayList<CurriculumBox> boxes;
    private ArrayList<Prerequisite> prerequisites;
    private Semester semester;

    public Curriculum(int id) {
        super(id);
    }
}
