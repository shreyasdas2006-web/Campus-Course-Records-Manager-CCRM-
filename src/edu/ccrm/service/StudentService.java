package edu.ccrm.service;

import edu.ccrm.config.AppConfig;
import edu.ccrm.model.Student;
import java.util.List;

/**
 * Contains business logic for student management.
 */
public class StudentService {

    private AppConfig appConfig = AppConfig.getInstance();

    public void addStudent(Student student) {
        appConfig.getAllStudents().add(student);
        System.out.println("Student added: " + student.getName());
    }

    public Student findStudentById(int id) {
        for (Student student : appConfig.getAllStudents()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void printAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = appConfig.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students in the system yet.");
        } else {
            for (Student student : students) {
                System.out.println(student.getDetails());
            }
        }
        System.out.println("--------------------\n");
    }
}
