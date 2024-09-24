import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and setters for the student attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nRoll Number: " + rollNumber + "\nGrade: " + grade + "\n";
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public List<Student> searchStudent(String query) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
            System.out.println("Students saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Students loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }
}

public class Student_Management_System {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

        while (true) {
            System.out.println("Student Management System Menu:");
            System.out.println("1. Add a Student");
            System.out.println("2. Remove a Student");
            System.out.println("3. Search for a Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Students to File");
            System.out.println("6. Load Students from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    Student student = new Student(name, rollNumber, grade);
                    studentManagementSystem.addStudent(student);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Roll Number to Remove: ");
                    int rollNumberToRemove = scanner.nextInt();
                    studentManagementSystem.removeStudent(rollNumberToRemove);
                    System.out.println("Student removed successfully.");
                    break;
                case 3:
                    System.out.print("Enter Search Query: ");
                    String searchQuery = scanner.nextLine();
                    List<Student> searchResults = studentManagementSystem.searchStudent(searchQuery);
                    if (searchResults.isEmpty()) {
                        System.out.println("No matching students found.");
                    } else {
                        System.out.println("Matching Students:");
                        for (Student result : searchResults) {
                            System.out.println(result);
                        }
                    }
                    break;
                case 4:
                    List<Student> allStudents = studentManagementSystem.getAllStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println("Student list is empty.");
                    } else {
                        System.out.println("All Students:");
                        for (Student student1 : allStudents) {
                            System.out.println(student1);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter File Name to Save Students: ");
                    String saveFileName = scanner.nextLine();
                    studentManagementSystem.saveToFile(saveFileName);
                    break;
                case 6:
                    System.out.print("Enter File Name to Load Students: ");
                    String loadFileName = scanner.nextLine();
                    studentManagementSystem.loadFromFile(loadFileName);
                    break;
                case 7:
                    System.out.println("Exiting Student Management System.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
