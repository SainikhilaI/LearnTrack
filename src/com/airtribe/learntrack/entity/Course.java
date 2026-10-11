package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.CourseStatus;

public class Course {

    private int courseId;
    private String courseName;
    private String description;
    private int  durationInWeeks;
    private CourseStatus status;

    public Course (){
        this.status = CourseStatus.ACTIVE;
    }

    public Course(int courseId, String courseName, String description,int durationInWeeks){
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.status = CourseStatus.ACTIVE;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

   public boolean isActive(){
   return this.status == CourseStatus.ACTIVE;
   } 

   public String getCourseDetails(){

    return "[Course ID: " + courseId + "] " + courseName + " (" + durationInWeeks + " weeks) - " 
                + description + " | Status: " + status;
   }
    
}
