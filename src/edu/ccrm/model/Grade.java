package edu.ccrm.model;

/**
 * Represents student grades and their corresponding grade points.
 */
public enum Grade {
    S(10.0),
    A(9.0),
    B(8.0),
    C(7.0),
    D(6.0),
    E(5.0),
    F(0.0);

    private final double gradePoints;

    Grade(double points) {
        this.gradePoints = points;
    }

    // This is the corrected method name
    public double getGradePoints() {
        return gradePoints;
    }

    /**
     * Converts numerical marks into a letter grade.
     * 
     * @param marks The marks obtained (0-100).
     * @return The corresponding Grade enum.
     */
    public static Grade fromMarks(int marks) {
        if (marks >= 90)
            return S;
        if (marks >= 80)
            return A;
        if (marks >= 70)
            return B;
        if (marks >= 60)
            return C;
        if (marks >= 50)
            return D;
        if (marks >= 40)
            return E;
        return F;
    }

    @Override
    public String toString() {
        return name() + " (" + gradePoints + " points)";
    }
}