package com.airtribe.learntrack.repository;

import java.util.ArrayList;

import java.util.List;

import com.airtribe.learntrack.entity.Course;

public class CourseRepo{
    public final List<Course>listOfCourses = new ArrayList<>();

    public void save(Course course){
        listOfCourses.add(course);
    }

    public List<Course> findAll(){
        return new ArrayList<>(listOfCourses);
    }

    public Course findById(int id){
        for(Course c : listOfCourses){
            if(c.getCourseId() == id){
                return c;
            }
        }
        return null;
    }
}