package com.airtribe.learntrack.service;



import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepo;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class StudentService {
    private final StudentRepo studentRepository;

    public StudentService(StudentRepo studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) throws InvalidInputException {
        InputValidator.validateNonEmpty(firstName, "First Name");
        InputValidator.validateNonEmpty(lastName, "Last Name");

        int id = IdGenerator.getNextStudentId();
        //Student student = (email == null || email.trim().isEmpty())
                //? new Student(id, firstName, lastName, batch)
          Student student =   new Student(id, firstName, lastName, email, batch);

        studentRepository.save(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws EntityNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student with ID " + id + " was not found.");
        }
        return student;
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setActive(false);
    }
}
