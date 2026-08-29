package oop_programming_construct.class_problems;

public class SrmStudent {
    private static String collegeName;
    private static String academicYear;
    private String name;

    static {
        collegeName = "SRM";
        academicYear = "2026";
        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + this.name);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};
        for (String n : names) {
            new SrmStudent(n);
        }
    }
}