package com.airtribe.learntrack.entity;
import com.airtribe.learntrack.enums.EnrollmentStatus;

public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private EnrollmentStatus status;
    
    public Enrollment(int enrollmentId,int studentId,int courseId,String enrollmentDate){
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status - EnrollmentStatus.ACTIVE;
}

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

public void setStatus(EnrollmentStatus status){
    this.status = status;
}

public String getEnrollmentDetails() {
    return "Enrollment [enrollmentId=" + enrollmentId + ", studentId=" + studentId + ", courseId=" + courseId
            + ", enrollmentDate=" + enrollmentDate + ", status=" + status + "]";
}


    
}
