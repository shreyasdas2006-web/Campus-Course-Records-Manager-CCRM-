package edu.ccrm.model;

/**
 * Represents a course, using the Builder pattern for object creation.
 */
public class Course {

    private String code;
    private String title;
    private int credits;
    private Instructor instructor;
    private Semester semester;

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
    }

    /**
     * Static nested Builder class for creating Course instances.
     */
    public static class Builder {
        private String code;
        private String title;
        private int credits = 3;
        private Instructor instructor = null;
        private Semester semester = Semester.FALL;

        public Builder(String code) {
            this.code = code;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }

    // --- Getters and Setters for the Course class ---
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        String instrName = (instructor == null) ? "TBD" : instructor.getName();
        return "Course: [" + code + "] " + title + " (" + credits + " credits, Instr: " + instrName + ")";
    }
}
