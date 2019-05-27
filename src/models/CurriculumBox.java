package models;

import java.util.ArrayList;

public class CurriculumBox {
    private CurriculumBoxPassingStrategy passingStrategy;
    private ArrayList<CurriculumRow> rows;

    boolean hasCourse(Course course) {
        for (CurriculumRow row : rows) {
            if (row instanceof SpecificCourse &&
                    ((SpecificCourse) row).getCourse().equals(course)) {
                return true;
            }
        }
        return false;
    }
}
