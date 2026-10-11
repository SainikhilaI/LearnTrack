package com.airtribe.learntrack.service;



import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepo;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class EnrollmentService {
    private final EnrollmentRepo enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepo enrollmentRepository, 
                             StudentService studentService, 
                             CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId, String date) 
            throws EntityNotFoundException, InvalidInputException {

        InputValidator.validateDate(date);

        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll student ID " + studentId + " because they are INACTIVE.");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in course ID " + courseId + " because it is not ACTIVE.");
        }

        int id = IdGenerator.getNextEnrollment();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, date);
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) throws EntityNotFoundException {
        studentService.getStudentById(studentId); // Verify student existence
        return enrollmentRepository.findByStudentId(studentId);
    }

    public void updateStatus(int enrollmentId, EnrollmentStatus status) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment record ID " + enrollmentId + " not found.");
        }
        enrollment.setStatus(status);
    }
}
