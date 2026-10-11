package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepo;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class CourseService {
    private final CourseRepo courseRepository;

    public CourseService(CourseRepo courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String name, String description, int duration) throws InvalidInputException {
        InputValidator.validateNonEmpty(name, "Course Name");
        if (duration <= 0) {
            throw new InvalidInputException("Course duration must be greater than 0 weeks.");
        }

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, name, description, duration);
        courseRepository.save(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) throws EntityNotFoundException {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with ID " + id + " was not found.");
        }
        return course;
    }

    public void setCourseStatus(int id, CourseStatus status) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setStatus(status);
    }
}
