package services;

import models.CourseOffering;

public class CourseOfferingRequest {
    CourseOffering courseOffering;
    CourseOfferingAction action;

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public CourseOfferingAction getAction() {
        return action;
    }
}
