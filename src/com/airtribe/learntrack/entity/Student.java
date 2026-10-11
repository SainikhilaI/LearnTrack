package com.airtribe.learntrack.entity;

public class Student extends Person{
   
    private String batch;
    private boolean active;

    public Student(){
        super();
        this.active = true;

    }
    public Student(int id, String firstName, String lastName, String email, String batch){
        this.batch = batch;
        this.active = true;

    }
    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
 public String getStudentDetails(){

    return "[StudentID :"+getId()+"Student Fullname :"+getFirstName()+" "+getLastName()+"Student email :"+getEmail()+"]";

 }
    
}
