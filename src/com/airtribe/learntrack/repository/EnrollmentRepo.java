package com.airtribe.learntrack.repository;
import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepo{
    private final List<Enrollment> listOfEnrollments = new ArrayList<>();

    public void save(Enrollment enrollment) {
        listOfEnrollments.add(enrollment);
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(listOfEnrollments);
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : listOfEnrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }
    public Enrollment findById(int id) {
        for (Enrollment e : listOfEnrollments) {
            if (e.getEnrollmentId() == id) {
                return e;
            }
        }
        return null;
    }


}