package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentIdGenerator = 100;
    private static int courseIdGenerator = 500;
    private static int enrollmentIdGenerator = 1000;

    public static int getNextStudentId(){
        return ++studentIdGenerator;
    }
    public static int getNextCourseId(){
        return ++courseIdGenerator;
    }

    public static int getNextEnrollment(){
        return ++enrollmentIdGenerator;
    }

    
}
