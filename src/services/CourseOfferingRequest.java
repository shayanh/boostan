package services;

import models.CourseOffering;

public class CourseOfferingRequest {
    private CourseOffering courseOffering;
    private CourseOfferingAction action;

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public CourseOfferingAction getAction() {
        return action;
    }
}
