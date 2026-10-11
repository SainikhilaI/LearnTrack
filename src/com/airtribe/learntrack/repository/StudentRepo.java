package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo{
    private final List<Student> studentStore = new ArrayList<>();

    public void save(Student student){
        studentStore.add(student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(studentStore);
    }

    public Student findById(int id){
        for(Student s : studentStore){
            if(s.getId() == id){
                return s;
            }
        }
        return null;

    }
    

}