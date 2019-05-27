package models;

import java.util.ArrayList;
import java.util.Date;

public class StudentSemester extends Entity {
    private Date registerStartTime;
    private Date editingStartTime;
    private ArrayList<Enrollment> enrollments;
    private Semester semester;

    public StudentSemester(int id) {
        super(id);
    }

}
