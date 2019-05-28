package models;

import java.util.ArrayList;

public class Curriculum extends Entity {
    private Integer majorID;
    private ArrayList<CurriculumBox> boxes;
    private ArrayList<Prerequisite> prerequisites = new ArrayList<>();
    private Integer semesterID;

    public Curriculum(Integer majorID, ArrayList<CurriculumBox> boxes, ArrayList<Prerequisite> prerequisites, Integer semesterID) {
        this.majorID = majorID;
        this.boxes = boxes;
        this.prerequisites = prerequisites;
        this.semesterID = semesterID;
    }

    public boolean hasCourse(Course course) {
        for (CurriculumBox box: boxes) {
            if (box.hasCourse(course)) {
                return true;
            }
        }
        return false;
    }

    public CurriculumRow getCorrespondingRow(Course course) throws IllegalArgumentException{
        for (CurriculumBox box: boxes) {
            CurriculumRow row = box.getCorrespondingRow(course);
            if (row != null)
                return row;
        }
        throw new IllegalArgumentException("Course not Found in Curriculum");
    }

    public boolean isPrerequisiteSatisfied(Student student, Course course) {
        for (Prerequisite prerequisite: prerequisites) {
            if (prerequisite.getTargetCourse().equals(course) && !prerequisite.isSatisfied(student)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrerequisiteSatisfied(Student student, Course course, ArrayList<CourseOffering> selectedOffers) {
        for (Prerequisite prerequisite : prerequisites) {
            if (prerequisite.getTargetCourse().equals(course) && !prerequisite.isSatisfied(student, selectedOffers)) {
                return false;
            }
        }
        return true;
    }
}
