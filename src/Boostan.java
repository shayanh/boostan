import models.*;
import respositories.RepositoryContainer;
import services.CourseOfferingAction;
import services.CourseOfferingRequest;
import services.CourseSelectionService;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Boostan {
    public static void main(String[] args) {
        try {
            Faculty faculty = new Faculty("Computer Engineering");
            RepositoryContainer.facultyRepository.insert(faculty);

            Degree degree = new Degree("Bachelors");
            RepositoryContainer.degreeRepository.insert(degree);

            Major major = new Major("Software Engineering", degree.getId(), faculty.getId());
            RepositoryContainer.majorRepository.insert(major);

            Course course1 = new Course("math1", false);
            RepositoryContainer.courseRepository.insert(course1);
            Course course2 = new Course("math2", false);
            RepositoryContainer.courseRepository.insert(course2);
            Course course3 = new Course("physics1", false);
            RepositoryContainer.courseRepository.insert(course3);

            CourseOffering offering1 = new CourseOffering(10, new ArrayList<>(), new ArrayList<>(), course1);
            RepositoryContainer.semesterRepository.addOffering(offering1);
            CourseOffering offering2 = new CourseOffering(10, new ArrayList<>(), new ArrayList<>(), course2);
            RepositoryContainer.semesterRepository.addOffering(offering2);
            CourseOffering offering3 = new CourseOffering(10, new ArrayList<>(), new ArrayList<>(), course3);
            RepositoryContainer.semesterRepository.addOffering(offering3);

            ArrayList<CourseOffering> offerings = new ArrayList<>();
            offerings.add(offering1);
            offerings.add(offering2);
            offerings.add(offering3);
            Semester semester = new Semester(offerings);
            RepositoryContainer.semesterRepository.insert(semester);

            HashMap<CreditType, Float> normalCourseCredits = new HashMap<>();
            normalCourseCredits.put(CreditType.THEORY, 3F);

            CurriculumRow row1 = new SpecificCourse(normalCourseCredits, course1);
            CurriculumRow row2 = new SpecificCourse(normalCourseCredits, course2);
            CurriculumRow row3 = new SpecificCourse(normalCourseCredits, course3);
            ArrayList<CurriculumRow> rows = new ArrayList<>(Arrays.asList(row1, row2, row3));
            ArrayList<CurriculumBox> boxes = new ArrayList<>(Collections.singletonList(new CurriculumBox(rows)));

            CoursePrerequisite prerequisite = new CoursePrerequisite(course2, course1);
            ArrayList<Prerequisite> prerequisites = new ArrayList<>();
            prerequisites.add(prerequisite);

            Curriculum curriculum = new Curriculum(major.getId(), boxes, prerequisites, semester.getId());
            RepositoryContainer.curriculumRepository.insert(curriculum);

            Person person = new Person("navid", "danesh", "0021231500");
            Student student = new Student("810194534", person, major.getId(), curriculum.getId());
            RepositoryContainer.studentRepository.insert(student);

            student.createSemester();

            CourseSelectionService service = new CourseSelectionService();
            for (CourseOffering offering: service.getOfferingsFor(student)) {
                System.out.println(offering.getCourse().getName());
            }

            ArrayList<CourseOfferingRequest> requests = new ArrayList<>();
            requests.add(
                    new CourseOfferingRequest(offering1, CourseOfferingAction.ENROLL)
            );
            requests.add(
                    new CourseOfferingRequest(offering2, CourseOfferingAction.ENROLL)
            );
            service.selectOfferingsFor(student, requests);

        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
    }
}