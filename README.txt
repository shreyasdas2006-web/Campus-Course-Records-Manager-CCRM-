================================================================================
  CAMPUS COURSE & RECORDS MANAGER (CCRM)
================================================================================
  A console-based Java SE application for managing students, courses,
  enrollments, grades & transcripts.

  Version  : 1.0
  Language : Java SE 17+
  Type     : Console Application
  Patterns : OOP | Singleton | Builder
  GitHub   : https://github.com/shreyasdas2006-web/Campus-Course-Records-Manager-CCRM-

================================================================================
  TABLE OF CONTENTS
================================================================================
  1.  Project Statement
  2.  Key Features
  3.  Requirements
  4.  Build & Run
  5.  Project Structure
  6.  OOP & Design Patterns
  7.  Advanced Java Features
  8.  Grading System
  9.  Console Menu
  10. Syllabus Mapping
  11. Java Editions & JDK Explained
  12. Evolution of Java
  13. Screenshots
  14. Documentation

================================================================================
  1. PROJECT STATEMENT
================================================================================
Design and implement a console-based Java application called Campus Course &
Records Manager (CCRM) that lets an institute manage:

  - Students       : add, list, update, enroll/unenroll, print transcripts
  - Courses        : create, list, update, search, assign instructors
  - Grades/GPA     : record marks, compute GPA, generate transcript view
  - File Utilities : export CSV, backup course data with timestamped folders
  - Console Menu   : menu-driven workflow for all operations

This is a Java SE project built and run locally. It demonstrates OOP principles
(Encapsulation, Inheritance, Abstraction, Polymorphism), Exception Handling,
Java I/O (NIO.2), Date/Time API, Streams & Lambdas, Enums, and Design Patterns
(Singleton, Builder).

================================================================================
  2. KEY FEATURES
================================================================================
  Feature                  Description
  -----------------------  ---------------------------------------------------
  Student Management       Add, list students; auto-seeded with sample data
  Course Management        Create & list courses; search by instructor/semester
  Enrollment System        Enroll students; enforce max-credit cap (21 credits)
  Marks & Grading          Record marks (0-100); auto-convert to grade (S-F)
  Transcript & GPA         View full academic transcript with cumulative GPA
  CSV Export               Export student & course data to structured CSV files
  Backup System            One-click backup with auto-generated timestamped folders
  Custom Exceptions        Business-rule exceptions for duplicate enrollment &
                           credit overload
  Design Patterns          Singleton (AppConfig) + Builder (Course)

================================================================================
  3. REQUIREMENTS
================================================================================
  Requirement    Specification
  -------------- -----------------------------------------------
  JDK            Java 17 or higher (LTS)
  IDE            IntelliJ IDEA / Eclipse (recommended)
  OS             Windows / macOS / Linux
  Build Tool     Manual javac (no Maven/Gradle needed)
  Dependencies   None -- 100% standard Java SE

================================================================================
  4. BUILD & RUN
================================================================================

  Step 1: Clone the repository
  ----------------------------
    git clone https://github.com/shreyasdas2006-web/Campus-Course-Records-Manager-CCRM-.git

  Step 2: Navigate into the project directory
  ----------------------------
    cd Campus-Course-Records-Manager-CCRM-

  Step 3: Compile from the project root
  ----------------------------
    javac -d out -sourcepath src src/edu/ccrm/CrmApp.java

  Step 4: Run the application
  ----------------------------
    java -cp out edu.ccrm.CrmApp

  NOTE: Sample CSV data files for testing are available inside proj_data/

  Install JDK on Windows:
  ----------------------------
    1. Download JDK 17+ from: https://www.oracle.com/java/technologies/downloads/
    2. Set environment variables:
         JAVA_HOME = C:\Program Files\Java\jdk-17
         Add %JAVA_HOME%\bin to PATH
    3. Verify: java -version  and  javac -version
    4. Open the project in IntelliJ IDEA or Eclipse and run CrmApp.java

================================================================================
  5. PROJECT STRUCTURE
================================================================================

  Campus-Course-Records-Manager-CCRM-/
  |
  +-- src/edu/ccrm/                       <- Main source root
  |   +-- CrmApp.java                     <- Entry point & console menu
  |   +-- config/
  |   |   +-- AppConfig.java              <- Singleton: central in-memory store
  |   +-- model/                          <- Domain entity classes
  |   |   +-- Person.java                 <- Abstract base class (id, name, email)
  |   |   +-- Student.java                <- Extends Person; holds enrolled courses
  |   |   +-- Instructor.java             <- Extends Person; adds department
  |   |   +-- Course.java                 <- Builder pattern; code, title, credits
  |   |   +-- Enrollment.java             <- Links Student <-> Course with grade & date
  |   |   +-- Grade.java                  <- Enum: S/A/B/C/D/F with grade points
  |   |   +-- Semester.java               <- Enum: FALL, SPRING, SUMMER
  |   +-- service/                        <- Business logic layer
  |   |   +-- StudentService.java         <- Add, find, list students
  |   |   +-- CourseService.java          <- Add, find, list courses
  |   |   +-- EnrollmentService.java      <- Enroll, grade, GPA, transcript
  |   +-- util/
  |       +-- exception/
  |       |   +-- DuplicateEnrollmentException.java
  |       |   +-- MaxCreditLimitExceededException.java
  |       +-- io/
  |           +-- FileHandler.java        <- CSV export, backup, folder size (NIO.2)
  |
  +-- ccrm_data/                          <- Runtime-generated export files
  |   +-- students_export.csv
  |   +-- courses_export.csv
  +-- proj_data/                          <- Sample seed data for testing
  |   +-- students_sample.csv
  |   +-- courses_sample.csv
  +-- screenshots/                        <- App screenshots
  +-- Project_Report.md                   <- Full project report
  +-- Usage.md                            <- Step-by-step usage guide
  +-- README.md                           <- Markdown version of this file
  +-- README.txt                          <- This file

================================================================================
  6. OOP & DESIGN PATTERNS
================================================================================

  Inheritance Hierarchy:
  ----------------------
    Person  (abstract)
    +-- Student      -> adds regNo, enrolledCourses list
    +-- Instructor   -> adds department

  Four Pillars of OOP:
  --------------------
  Principle       Implementation
  --------------  ----------------------------------------------------------
  Encapsulation   All model fields are private with public getters/setters
  Inheritance     Student and Instructor both extend abstract Person
  Abstraction     Person defines abstract getDetails() -- must be implemented
  Polymorphism    getDetails() behaves differently in Student vs Instructor

  Design Pattern 1: Singleton -- AppConfig
  ----------------------------------------
  Ensures a single shared in-memory data store (students, courses, enrollments,
  instructors) is used across all service classes.

    public static AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

  Design Pattern 2: Builder -- Course
  ------------------------------------
  Enables clean, readable object construction without telescoping constructors.

    Course c1 = new Course.Builder("CS101")
            .title("Intro to Programming")
            .credits(4)
            .semester(Semester.FALL)
            .instructor(profGupta)
            .build();

================================================================================
  7. ADVANCED JAVA FEATURES
================================================================================
  Feature                Where Used
  ---------------------  -------------------------------------------------------
  Generics               List<Student>, List<Course>, List<Enrollment> in services
  Collections            ArrayList for dynamic in-memory storage of all data
  Enums                  Grade (with fromMarks() + grade points), Semester
  Streams & Lambdas      findStudentById(), findCourseByCode() via filter/findFirst
  Date/Time API          LocalDate used in Enrollment for enrollment date
  Java NIO.2             Path, Paths, Files in FileHandler.java for all file ops
  Custom Exceptions      DuplicateEnrollmentException, MaxCreditLimitExceededException
  Assertions             Constructor-level invariant checks (assert id > 0)
  Recursion              getFolderSize() recursively walks the backup directory tree

================================================================================
  8. GRADING SYSTEM
================================================================================
  Marks are automatically converted to a letter grade using the Grade enum.

  Grade   Marks Range    Grade Points
  ------  -------------  ------------
  S       90 - 100       10.0
  A       80 - 89         9.0
  B       70 - 79         8.0
  C       60 - 69         7.0
  D       50 - 59         6.0
  F       Below 50        0.0

  GPA Formula:
    GPA = Sum(Grade Points x Course Credits) / Sum(Course Credits)

================================================================================
  9. CONSOLE MENU
================================================================================

  --- Main Menu ---
  1. Add New Student
  2. List All Students
  3. List All Courses
  4. Enroll a Student
  5. Record Marks for a Student
  6. View Student Transcript
  7. Export Data to CSV
  8. Create a Backup
  9. Exit
  --------------------
  Enter your choice:

  Typical Workflow:
    1. Add student          -> Option 1
    2. Enroll in course     -> Option 4
    3. Record marks         -> Option 5
    4. View transcript/GPA  -> Option 6
    5. Export & backup      -> Options 7 / 8

  See Usage.md for a detailed step-by-step walkthrough with sample output.

================================================================================
  10. SYLLABUS MAPPING
================================================================================
  Syllabus Topic               Implementation in CCRM
  ---------------------------  -------------------------------------------------
  OOP - Encapsulation          Private fields + getters/setters in all models
  OOP - Inheritance            Student, Instructor extend Person
  OOP - Abstraction            Abstract class Person with abstract getDetails()
  OOP - Polymorphism           Overridden getDetails() in Student and Instructor
  Packages                     edu.ccrm.model, edu.ccrm.service, edu.ccrm.util
  Exception Handling           DuplicateEnrollmentException,
                               MaxCreditLimitExceededException; try-catch throughout
  Collections Framework        ArrayList<> in AppConfig and all service classes
  Generics                     List<Student>, List<Course>, List<Enrollment>
  Enums                        Grade (with methods), Semester
  Streams & Lambdas            stream().filter().findFirst() for lookups
  Date/Time API                LocalDate enrollmentDate in Enrollment.java
  File I/O (NIO.2)             FileHandler.java -- CSV export, backup, Files.walk()
  Recursion                    getFolderSize() walks directory tree recursively
  Assertions                   Constructor invariants (assert id > 0)
  Design Pattern - Singleton   AppConfig.getInstance()
  Design Pattern - Builder     Course.Builder

================================================================================
  11. JAVA EDITIONS & JDK EXPLAINED
================================================================================

  Java ME vs SE vs EE:
  --------------------
  Edition               Purpose                          Example Use Cases
  --------------------  -------------------------------  ----------------------
  ME (Micro Edition)    Lightweight, constrained devices  Embedded, feature phones
  SE (Standard Edition) Core Java libraries + APIs        Desktop, CLI apps (CCRM)
  EE (Enterprise Ed.)   Adds web & enterprise APIs        Servlets, Jakarta EE

  JDK -> JRE -> JVM:
  -------------------
    JDK  (Java Development Kit)
     +-- JRE  (Java Runtime Environment)
          +-- JVM  (Java Virtual Machine)

    JVM : Executes compiled .class bytecode on any OS
    JRE : JVM + standard libraries to run Java apps
    JDK : JRE + javac compiler + dev tools to build Java apps

================================================================================
  12. EVOLUTION OF JAVA
================================================================================
  Year     Milestone
  -------  -------------------------------------------------------------------
  1995     Java 1.0 -- "Write once, run anywhere"
  1998     Java 2: J2SE, J2EE, J2ME introduced
  2004     Java 5 -- Generics, Annotations, Enums
  2014     Java 8 -- Streams, Lambdas, Date/Time API
  2017     Java 9 -- Module system (Jigsaw)
  2021     Java 17 LTS -- Records, Sealed Classes
  2023     Java 21 LTS -- Pattern Matching, Virtual Threads

================================================================================
  13. SCREENSHOTS
================================================================================
  Screenshots are located in the screenshots/ folder:

    screenshots/java_version_check.png  -> Java installation verification
    screenshots/menu.png                -> Application main menu
    screenshots/file_struct.png         -> Project file structure
    screenshots/menu_test.png           -> Sample menu test run

================================================================================
  14. DOCUMENTATION
================================================================================
  File                Description
  ------------------  ----------------------------------------------------------
  README.md           Markdown version -- project overview & reference
  README.txt          This file -- plain text version
  Usage.md            Step-by-step usage walkthrough with console output examples
  Project_Report.md   Full academic project report (17 sections)

================================================================================
  Thank you for checking out CCRM!
  Feel free to open issues or contribute via pull requests.
  GitHub: https://github.com/shreyasdas2006-web/Campus-Course-Records-Manager-CCRM-
================================================================================
