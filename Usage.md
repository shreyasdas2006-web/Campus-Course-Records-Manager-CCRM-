
-----

# CCRM Usage Guide

This guide provides a simple walkthrough of the main features of the Campus Course & Records Manager application. The program operates through a numbered menu in the console.

### Common Workflow Example

Here is a typical sequence of actions to demonstrate how the system works.

#### 1\. Add a New Student

From the main menu, select option `1` to add a new student to the system.

```text
--- Main Menu ---
1. Add New Student
...
9. Exit
--------------------
Enter your choice: 1

--- Add New Student ---
Enter Student ID (a unique number): 3
Enter Full Name: Amit Patel
Enter Email: amit.p@email.com
Enter Registration Number (e.g., REG103): REG103

Student added: Amit Patel
```

#### 2\. Enroll the Student in a Course

Next, use option `4` to enroll the newly created student (ID `3`) into a course (`CS101`).

```text
Enter your choice: 4

Enter Student ID to enroll: 3
Enter Course Code (e.g., CS101): CS101

Success! Amit Patel is now enrolled in Intro to Programming
```

#### 3\. Record Marks for the Student

After the course is completed, you can assign marks using option `5`.

```text
Enter your choice: 5

--- Record Student Marks ---
Enter Student ID: 3
Enter Course Code: CS101
Enter Marks (0-100): 92

Marks recorded. Amit Patel gets grade S for Intro to Programming
```

#### 4\. View the Student's Transcript

Finally, use option `6` to view the student's full academic record, including their calculated GPA.

```text
Enter your choice: 6

--- View Student Transcript ---
Enter Student ID: 3

----------------------------------------
Transcript for: Amit Patel (REG103)
----------------------------------------
Course: Intro to Programming   | Grade: S
----------------------------------------
Cumulative GPA: 10.00
----------------------------------------
```

### Data File Formats

The application can export data to `.csv` files. The format for these files is shown below.

#### `students_export.csv`

```csv
ID,RegistrationNumber,FullName,Email
1,REG101,Ravi Kumar,ravi.k@email.com
2,REG102,Priya Singh,priya.s@email.com
```

#### `courses_export.csv`

```csv
Code,Title,Credits,InstructorName
CS101,Intro to Programming,4,Dr. Gupta
PHY202,Quantum Physics,3,Dr. Sharma
```