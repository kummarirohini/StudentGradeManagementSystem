
import java.io.*;
import java.util.*;

// Main class to run the program
public class StudentGradeManagementSystem {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            GradeManager manager = new GradeManager();
            int choice;

            System.out.println("=== STUDENT GRADE MANAGEMENT SYSTEM ===");

            do {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. Add Student");
                System.out.println("2. Add Grade for Student");
                System.out.println("3. Calculate Student GPA");
                System.out.println("4. Generate Student Report");
                System.out.println("5. Display All Students");
                System.out.println("6. Save Data to File");
                System.out.println("7. Load Data from File");
                System.out.println("8. Exit");
                System.out.print("Enter your choice (1-8): ");

                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 ->
                        manager.addStudent(scanner);
                    case 2 ->
                        manager.addGrade(scanner);
                    case 3 ->
                        manager.calculateGPA(scanner);
                    case 4 ->
                        manager.generateReport(scanner);
                    case 5 ->
                        manager.displayAllStudents();
                    case 6 ->
                        manager.saveToFile();
                    case 7 ->
                        manager.loadFromFile();
                    case 8 ->
                        System.out.println("Exiting system. Goodbye!");
                    default ->
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 8);
        }
    }
}

// Student class using encapsulation
class Student {

    private final String studentId;
    private final String name;
    private final Map<String, Double> grades; // subject -> grade
    private double gpa;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.grades = new HashMap<>();
        this.gpa = 0.0;
    }

    // Getters and Setters (Encapsulation)
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public void addGrade(String subject, double grade) {
        grades.put(subject, grade);
        calculateGPA();
    }

    public Map<String, Double> getGrades() {
        return new HashMap<>(grades); // Return copy for encapsulation
    }

    private void calculateGPA() {
        if (grades.isEmpty()) {
            gpa = 0.0;
            return;
        }

        double total = 0;
        for (double grade : grades.values()) {
            total += grade;
        }
        gpa = total / grades.size();
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, GPA: %.2f", studentId, name, gpa);
    }
}

// GradeManager class to handle operations
class GradeManager {

    private final Map<String, Student> students;
    private static final String FILE_NAME = "student_data.txt";

    public GradeManager() {
        students = new HashMap<>();
    }

    public void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        if (students.containsKey(id)) {
            System.out.println("Student with this ID already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(id, name);
        students.put(id, student);
        System.out.println("Student added successfully!");
    }

    public void addGrade(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("No students available. Please add a student first.");
            return;
        }

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        Student student = students.get(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Subject: ");
        String subject = scanner.nextLine();

        System.out.print("Enter Grade (0-100): ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade! Please enter between 0-100.");
            return;
        }

        student.addGrade(subject, grade);
        System.out.println("Grade added successfully!");
    }

    public void calculateGPA(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        Student student = students.get(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.printf("GPA for %s: %.2f\n", student.getName(), student.getGpa());
    }

    public void generateReport(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        Student student = students.get(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("\n=== STUDENT REPORT ===");
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Name: " + student.getName());
        System.out.println("Overall GPA: " + String.format("%.2f", student.getGpa()));

        Map<String, Double> grades = student.getGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades available for this student.");
        } else {
            System.out.println("\nSubject-wise Grades:");
            for (Map.Entry<String, Double> entry : grades.entrySet()) {
                System.out.printf("%s: %.2f\n", entry.getKey(), entry.getValue());
            }
        }
        System.out.println("=====================");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n=== ALL STUDENTS ===");
        for (Student student : students.values()) {
            System.out.println(student);
        }
        System.out.println("====================");
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students.values()) {
                writer.println(student.getStudentId() + "," + student.getName() + "," + student.getGpa());

                Map<String, Double> grades = student.getGrades();
                for (Map.Entry<String, Double> entry : grades.entrySet()) {
                    writer.println("GRADE," + entry.getKey() + "," + entry.getValue());
                }
            }
            System.out.println("Data saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            students.clear();
            Student currentStudent = null;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                if (parts[0].equals("GRADE")) {
                    if (currentStudent != null && parts.length == 3) {
                        currentStudent.addGrade(parts[1], Double.parseDouble(parts[2]));
                    }
                } else {
                    if (parts.length >= 3) {
                        currentStudent = new Student(parts[0], parts[1]);
                        students.put(parts[0], currentStudent);
                    }
                }
            }
            System.out.println("Data loaded from file successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
