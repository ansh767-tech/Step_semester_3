package Linked_List.class_problems;

import java.util.*;

/**
 * Problem 4: Class Marks Grid Analysis
 * 
 * Manage and analyze student marks in multiple subjects stored in a 2D array (grid).
 * Calculates student totals, subject averages (formatted to 2 decimal places),
 * and identifies the top-performing student.
 */
public class ClassMarksGridAnalysis {

    public static class StudentRecord {
        private String name;
        private int[] marks;

        public StudentRecord(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int[] getMarks() {
            return marks;
        }

        public int getTotal() {
            int sum = 0;
            for (int mark : marks) {
                sum += mark;
            }
            return sum;
        }
    }

    public static void analyzeMarks(List<StudentRecord> students) {
        if (students == null || students.isEmpty()) return;

        int numStudents = students.size();
        int numSubjects = students.get(0).getMarks().length;

        // 2D grid
        int[][] grid = new int[numStudents][numSubjects];
        for (int i = 0; i < numStudents; i++) {
            grid[i] = students.get(i).getMarks();
        }

        // Totals & Topper
        List<String> totalsParts = new ArrayList<>();
        StudentRecord topper = null;
        int maxTotal = Integer.MIN_VALUE;

        for (StudentRecord student : students) {
            int total = student.getTotal();
            totalsParts.add(student.getName() + " " + total);
            if (total > maxTotal) {
                maxTotal = total;
                topper = student;
            }
        }
        System.out.println("Totals " + String.join(", ", totalsParts));

        // Subject Averages
        List<String> averagesParts = new ArrayList<>();
        for (int col = 0; col < numSubjects; col++) {
            double subjectSum = 0;
            for (int row = 0; row < numStudents; row++) {
                subjectSum += grid[row][col];
            }
            double avg = subjectSum / numStudents;
            averagesParts.add(String.format(Locale.US, "%.2f", avg));
        }
        System.out.println("averages " + String.join(", ", averagesParts));

        // Topper
        if (topper != null) {
            System.out.println("topper " + topper.getName() + " (" + topper.getTotal() + ")");
        }
    }

    public static StudentRecord parseLine(String line) {
        line = line.trim();
        int openBracket = line.indexOf('[');
        int closeBracket = line.indexOf(']');
        String name = line.substring(0, openBracket).trim();
        String marksStr = line.substring(openBracket + 1, closeBracket).trim();
        String[] parts = marksStr.split(",");
        int[] marks = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            marks[i] = Integer.parseInt(parts[i].trim());
        }
        return new StudentRecord(name, marks);
    }

    public static void main(String[] args) {
        List<String> sampleInput = Arrays.asList(
            "Asha [78, 85, 90]",
            "Ravi [88, 92, 79]",
            "Neha [65, 70, 95]"
        );

        List<StudentRecord> students = new ArrayList<>();
        for (String line : sampleInput) {
            students.add(parseLine(line));
        }

        System.out.println("--- Execution Output ---");
        analyzeMarks(students);
    }
}
