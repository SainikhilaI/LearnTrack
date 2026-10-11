package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exceptions.EntityNotFoundException;
import com.airtribe.learntrack.exceptions.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepo;
import com.airtribe.learntrack.repository.EnrollmentRepo;
import com.airtribe.learntrack.repository.StudentRepo;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Initializing repository and service layers
    private static final StudentRepo studentRepo = new StudentRepo();
    private static final CourseRepo courseRepo = new CourseRepo();
    private static final EnrollmentRepo enrollmentRepo = new EnrollmentRepo();

    private static final StudentService studentService = new StudentService(studentRepo);
    private static final CourseService courseService = new CourseService(courseRepo);
    private static final EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepo, studentService, courseService);

    public static void main(String[] args) {
        insertInitialData();
        boolean running = true;

        System.out.println("Welcome to " + AppConstants.APP_NAME);

        while (running) {
            printMainMenu();
            int choice = readIntInput("Select an option: ");

            switch (choice) {
                case MenuOptions.MAIN_STUDENT_MGMT -> handleStudentMenu();
                case MenuOptions.MAIN_COURSE_MGMT -> handleCourseMenu();
                case MenuOptions.MAIN_ENROLLMENT_MGMT -> handleEnrollmentMenu();
                case MenuOptions.MAIN_EXIT -> {
                    System.out.println("\nExiting application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("\n[Error] Invalid option. Please select a valid menu choice.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=================================");
        System.out.println("   " + AppConstants.APP_NAME);
        System.out.println("=================================");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Exit");
    }

    private static void handleStudentMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Deactivate Student");
        int option = readIntInput("Choice: ");

        try {
            switch (option) {
                case MenuOptions.STUDENT_ADD -> {
                    System.out.print("First Name: ");
                    String fname = scanner.nextLine().trim();
                    System.out.print("Last Name: ");
                    String lname = scanner.nextLine().trim();
                    System.out.print("Email (Optional): ");
                    String email = scanner.nextLine().trim();
                    System.out.print("Batch: ");
                    String batch = scanner.nextLine().trim();

                    Student s = studentService.addStudent(fname, lname, email, batch);
                    System.out.println("[Success] Student Created: " + s.getStudentDetails());
                }
                case MenuOptions.STUDENT_VIEW_ALL -> {
                    List<Student> students = studentService.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        students.forEach(s -> System.out.println(" - " + s.getStudentDetails()));
                    }
                }
                case MenuOptions.STUDENT_SEARCH -> {
                    int id = readIntInput("Enter Student ID: ");
                    Student s = studentService.getStudentById(id);
                    System.out.println("[Found] " + s.getStudentDetails());
                }
                case MenuOptions.STUDENT_DEACTIVATE -> {
                    int id = readIntInput("Enter Student ID to deactivate: ");
                    studentService.deactivateStudent(id);
                    System.out.println("[Success] Student ID " + id + " set to INACTIVE.");
                }
                default -> System.out.println("[Error] Unknown student menu choice.");
            }
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    private static void handleCourseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add New Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Toggle Course Status");
        int option = readIntInput("Choice: ");

        try {
            switch (option) {
                case MenuOptions.COURSE_ADD -> {
                    System.out.print("Course Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Description: ");
                    String desc = scanner.nextLine().trim();
                    int weeks = readIntInput("Duration in weeks: ");

                    Course c = courseService.addCourse(name, desc, weeks);
                    System.out.println("[Success] Course Created: " + c.getCourseDetails());
                }
                case MenuOptions.COURSE_VIEW_ALL -> {
                    List<Course> courses = courseService.getAllCourses();
                    if (courses.isEmpty()) {
                        System.out.println("No courses found.");
                    } else {
                        courses.forEach(c -> System.out.println(" - " + c.getCourseDetails()));
                    }
                }
                case MenuOptions.COURSE_TOGGLE_STATUS -> {
                    int id = readIntInput("Enter Course ID: ");
                    System.out.println("Select Status: 1. ACTIVE | 2. INACTIVE | 3. ARCHIVED");
                    int st = readIntInput("Choice: ");
                    CourseStatus status = switch (st) {
                        case 2 -> CourseStatus.INACTIVE;
                        //case 3 -> CourseStatus.ARCHIVED;
                        default -> CourseStatus.ACTIVE;
                    };
                    courseService.setCourseStatus(id, status);
                    System.out.println("[Success] Course status updated.");
                }
                default -> System.out.println("[Error] Unknown course menu choice.");
            }
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    private static void handleEnrollmentMenu() {
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student");
        System.out.println("2. View Student Enrollments");
        System.out.println("3. Update Enrollment Status");
        int option = readIntInput("Choice: ");

        try {
            switch (option) {
                case MenuOptions.ENROLL_STUDENT -> {
                    int sId = readIntInput("Student ID: ");
                    int cId = readIntInput("Course ID: ");
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine().trim();

                    Enrollment e = enrollmentService.enrollStudent(sId, cId, date);
                    System.out.println("[Success] Enrollment Created: " + e.getEnrollmentDetails());
                }
                case MenuOptions.ENROLL_VIEW_BY_STUDENT -> {
                    int sId = readIntInput("Student ID: ");
                    List<Enrollment> list = enrollmentService.getEnrollmentsByStudent(sId);
                    if (list.isEmpty()) {
                        System.out.println("No enrollments found for Student ID " + sId);
                    } else {
                        list.forEach(e -> System.out.println(" - " + e.getEnrollmentDetails()));
                    }
                }
                case MenuOptions.ENROLL_UPDATE_STATUS -> {
                    int eId = readIntInput("Enrollment ID: ");
                    System.out.println("Status: 1. COMPLETED | 2. CANCELLED");
                    int st = readIntInput("Choice: ");
                    EnrollmentStatus status = (st == 1) ? EnrollmentStatus.COMPLETED : EnrollmentStatus.CANCELLED;

                    enrollmentService.updateStatus(eId, status);
                    System.out.println("[Success] Enrollment updated to " + status);
                }
                default -> System.out.println("[Error] Unknown enrollment menu choice.");
            }
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[Input Error] Please enter a valid numerical value.");
            }
        }
    }

    private static void insertInitialData() {
        try {
            studentService.addStudent("Alice", "Smith", "alice@example.com", "Cohort-1");
            studentService.addStudent("Bob", "Jones", "", "Cohort-1");
            courseService.addCourse("Core Java Fundamentals", "Master fundamental Java concepts", 4);
        } catch (Exception ignored) {}
    }
}