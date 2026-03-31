package edu.ccrm;

import edu.ccrm.config.AppConfig;
import edu.ccrm.model.*;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;
import edu.ccrm.util.exception.DuplicateEnrollmentException;
import edu.ccrm.util.exception.MaxCreditLimitExceededException;
import edu.ccrm.util.io.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CrmApp {

    private static final Path DATA_DIR = Paths.get(System.getProperty("user.dir"), "ccrm_data");

    public static void main(String[] args) {
        System.out.println("Welcome to CCRM - The Campus Course & Records Manager");
        System.out.println("Data will be saved in: " + DATA_DIR);

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();
        seedInitialData(studentService, courseService);

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleAddStudent(scanner, studentService);
                    break;
                case "2":
                    studentService.printAllStudents();
                    break;
                case "3":
                    courseService.printAllCourses();
                    break;
                case "4":
                    handleEnrollment(scanner, studentService, enrollmentService, courseService);
                    break;
                case "5":
                    handleRecordMarks(scanner, studentService, courseService, enrollmentService);
                    break; // NEW
                case "6":
                    handleViewTranscript(scanner, studentService, enrollmentService);
                    break; // NEW
                case "7":
                    handleExport();
                    break;
                case "8":
                    handleBackup();
                    break;
                case "9":
                    isRunning = false;
                    System.out.println("Goodbye! Thank you for using CCRM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add New Student");
        System.out.println("2. List All Students");
        System.out.println("3. List All Courses");
        System.out.println("4. Enroll a Student");
        System.out.println("5. Record Marks for a Student"); // NEW
        System.out.println("6. View Student Transcript"); // NEW
        System.out.println("7. Export Data to CSV");
        System.out.println("8. Create a Backup");
        System.out.println("9. Exit");
        System.out.println("--------------------");
    }

    private static void handleAddStudent(Scanner scanner, StudentService ss) {
        // ... (this method remains the same)
        try {
            System.out.println("\n--- Add New Student ---");
            System.out.print("Enter Student ID (a unique number): ");
            int id = Integer.parseInt(scanner.nextLine());
            if (ss.findStudentById(id) != null) {
                System.out.println("Error: A student with this ID already exists.");
                return;
            }
            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Registration Number (e.g., REG103): ");
            String regNo = scanner.nextLine();
            Student newStudent = new Student(id, name, email, regNo);
            ss.addStudent(newStudent);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format for Student ID.");
        }
    }

    private static void handleEnrollment(Scanner scanner, StudentService ss, EnrollmentService es, CourseService cs) {
        // ... (this method remains the same)
        try {
            System.out.print("Enter Student ID to enroll: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Course Code (e.g., CS101): ");
            String courseCode = scanner.nextLine();
            Student student = ss.findStudentById(studentId);
            Course course = cs.findCourseByCode(courseCode);
            if (student != null && course != null) {
                es.enrollStudent(student, course);
            } else {
                System.out.println("Error: Could not find a student or course with the provided details.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format for Student ID.");
        } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
            System.out.println("ENROLLMENT FAILED: " + e.getMessage());
        }
    }

    // --- NEW METHOD to handle recording marks ---
    private static void handleRecordMarks(Scanner scanner, StudentService ss, CourseService cs, EnrollmentService es) {
        try {
            System.out.println("\n--- Record Student Marks ---");
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            Student student = ss.findStudentById(studentId);

            if (student == null) {
                System.out.println("Error: Student not found.");
                return;
            }

            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            Course course = cs.findCourseByCode(courseCode);

            if (course == null) {
                System.out.println("Error: Course not found.");
                return;
            }

            System.out.print("Enter Marks (0-100): ");
            int marks = Integer.parseInt(scanner.nextLine());

            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks must be between 0 and 100.");
                return;
            }

            es.recordMarks(student, course, marks);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format for ID or marks.");
        }
    }

    // --- NEW METHOD to view a student's transcript ---
    private static void handleViewTranscript(Scanner scanner, StudentService ss, EnrollmentService es) {
        try {
            System.out.println("\n--- View Student Transcript ---");
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            Student student = ss.findStudentById(studentId);

            if (student == null) {
                System.out.println("Error: Student not found.");
                return;
            }

            System.out.println("\n----------------------------------------");
            System.out.println("Transcript for: " + student.getName() + " (" + student.getRegNo() + ")");
            System.out.println("----------------------------------------");

            List<Enrollment> enrollments = es.getEnrollmentsForStudent(student);

            if (enrollments.isEmpty()) {
                System.out.println("This student is not enrolled in any courses.");
            } else {
                for (Enrollment enrollment : enrollments) {
                    String grade = enrollment.getGrade() != null ? enrollment.getGrade().name() : "Not Graded";
                    System.out.printf("Course: %-20s | Grade: %s\n", enrollment.getCourse().getTitle(), grade);
                }
            }

            double gpa = es.computeGpa(student);
            System.out.println("----------------------------------------");
            System.out.printf("Cumulative GPA: %.2f\n", gpa);
            System.out.println("----------------------------------------");

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format for Student ID.");
        }
    }

    private static void handleExport() {
        // ... (this method remains the same)
        System.out.println("Exporting data...");
        try {
            List<Student> allStudents = AppConfig.getInstance().getAllStudents();
            List<Course> allCourses = AppConfig.getInstance().getAllCourses();
            List<String> studentHeader = Arrays.asList("ID", "RegistrationNumber", "FullName", "Email");
            List<List<String>> studentRows = new ArrayList<>();
            for (Student s : allStudents) {
                studentRows.add(Arrays.asList(String.valueOf(s.getId()), s.getRegNo(), s.getName(), s.getEmail()));
            }
            FileHandler.exportToCsv(DATA_DIR.resolve("students_export.csv"), studentHeader, studentRows);
            System.out.println("...Student data exported successfully!");
            List<String> courseHeader = Arrays.asList("Code", "Title", "Credits", "InstructorName");
            List<List<String>> courseRows = new ArrayList<>();
            for (Course c : allCourses) {
                String instrName = (c.getInstructor() == null) ? "TBD" : c.getInstructor().getName();
                courseRows.add(Arrays.asList(c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), instrName));
            }
            FileHandler.exportToCsv(DATA_DIR.resolve("courses_export.csv"), courseHeader, courseRows);
            System.out.println("...Course data exported successfully!");
        } catch (IOException e) {
            System.out.println("Error during file export: " + e.getMessage());
        }
    }

    private static void handleBackup() {
        // ... (this method remains the same)
        System.out.println("Creating backup...");
        try {
            Path backupPath = FileHandler.createBackupFolder(DATA_DIR);
            System.out.println("Backup folder created at: " + backupPath);
            Path studentFile = DATA_DIR.resolve("students_export.csv");
            Path courseFile = DATA_DIR.resolve("courses_export.csv");
            if (Files.exists(studentFile))
                Files.copy(studentFile, backupPath.resolve("students_export.csv"));
            if (Files.exists(courseFile))
                Files.copy(courseFile, backupPath.resolve("courses_export.csv"));
            System.out.println("Files copied to backup.");
            System.out.println("Total backup size (bytes): " + FileHandler.getFolderSize(backupPath));
        } catch (IOException e) {
            System.out.println("Error during backup: " + e.getMessage());
        }
    }

    private static void seedInitialData(StudentService ss, CourseService cs) {
        // ... (this method remains the same)
        AppConfig config = AppConfig.getInstance();
        Instructor profGupta = new Instructor(101, "Dr. Gupta", "gupta@uni.ac.in", "Computer Science");
        Instructor profSharma = new Instructor(102, "Dr. Sharma", "sharma@uni.ac.in", "Physics");
        config.getAllInstructors().add(profGupta);
        config.getAllInstructors().add(profSharma);
        ss.addStudent(new Student(1, "Ravi Kumar", "ravi.k@email.com", "REG101"));
        ss.addStudent(new Student(2, "Priya Singh", "priya.s@email.com", "REG102"));
        Course c1 = new Course.Builder("CS101").title("Intro to Programming").credits(4).semester(Semester.FALL)
                .instructor(profGupta).build();
        Course c2 = new Course.Builder("PHY202").title("Quantum Physics").credits(3).semester(Semester.SPRING)
                .instructor(profSharma).build();
        cs.addCourse(c1);
        cs.addCourse(c2);
    }
}