package Linked_List.class_problems;

import java.util.*;

/**
 * Problem 3: Student Club Registration Management
 * 
 * Develop a student club registration system that stores members by their unique roll number.
 * Prevents duplicate registrations by overriding equals() and hashCode() based only on rollNumber.
 */
public class StudentClubRegistrationManagement {

    public static class Student {
        private String rollNumber;
        private String name;

        public Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(rollNumber, student.rollNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rollNumber);
        }

        @Override
        public String toString() {
            return "Student[rollNumber=" + rollNumber + ", name=" + name + "]";
        }
    }

    public static class ClubRegistrationSystem {
        private Set<Student> members = new LinkedHashSet<>();
        private boolean countPrinted = false;

        public boolean addStudent(Student student) {
            if (members.contains(student)) {
                System.out.println("duplicate rejected");
                return false;
            } else {
                members.add(student);
                System.out.println("Added");
                return true;
            }
        }

        public void printMemberCount() {
            if (!countPrinted) {
                System.out.println("member count " + members.size());
                countPrinted = true;
            }
        }

        public boolean containsStudent(Student student) {
            printMemberCount();
            boolean found = members.contains(student);
            System.out.println("contains: " + found);
            return found;
        }

        public int getMemberCount() {
            return members.size();
        }
    }

    public static void processOperations(List<String> lines) {
        ClubRegistrationSystem system = new ClubRegistrationSystem();

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String command = parts[0];

            if (command.equalsIgnoreCase("ADD")) {
                String roll = parts[1];
                String name = parts[2];
                system.addStudent(new Student(roll, name));
            } else if (command.equalsIgnoreCase("CONTAINS")) {
                String roll = parts[1];
                String name = parts[2];
                system.containsStudent(new Student(roll, name));
            }
        }
        system.printMemberCount();
    }

    public static void main(String[] args) {
        List<String> sampleInput = Arrays.asList(
            "ADD 21CS01 Asha",
            "ADD 21CS01 Asha",
            "ADD 21CS02 Ravi",
            "CONTAINS 21CS02 Ravi",
            "CONTAINS 21CS03 Priya"
        );

        System.out.println("--- Execution Output ---");
        processOperations(sampleInput);
    }
}
