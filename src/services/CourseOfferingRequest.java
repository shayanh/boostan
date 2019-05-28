package services;

import models.CourseOffering;

public class CourseOfferingRequest {
    private CourseOffering courseOffering;
    private CourseOfferingAction action;

    public CourseOfferingRequest(CourseOffering courseOffering, CourseOfferingAction action) {
        this.courseOffering = courseOffering;
        this.action = action;
    }

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public CourseOfferingAction getAction() {
        return action;
    }
}
