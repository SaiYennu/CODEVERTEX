import java.util.Scanner;

class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the student name
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();

        // Ask for the student number
        System.out.print("Enter your student number: ");
        String studentNumber = scanner.nextLine();

        // Ask the user for the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        // Create arrays to store the subject names and marks
        String[] subjectNames = new String[numSubjects];
        int[] marks = new int[numSubjects];

        // Prompt the user for the subject names and marks
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter the name of subject " + (i + 1) + ": ");
            subjectNames[i] = scanner.nextLine();

            System.out.print("Enter the marks for subject " + subjectNames[i] + ": ");
            int mark = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            // Check for invalid marks and prompt the user to re-enter
            while (mark < 0 || mark > 100) {
                System.out.println("Invalid marks! Please enter a valid mark between 0 and 100.");
                System.out.print("Enter the marks for subject " + subjectNames[i] + ": ");
                mark = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character left by nextInt()
            }

            marks[i] = mark;
        }

        // Calculate average result
        double sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        double average = sum / numSubjects;

        // Output the personalized greeting and report card
        System.out.println("\nHello, " + studentName + "! Welcome to Grade Calculator!.");
        System.out.println("Student Number: " + studentNumber + "\n");
        System.out.println("Report Card For " + studentName + ":");
        for (int i = 0; i < numSubjects; i++) {
            System.out.println("    " + subjectNames[i] + ": " + marks[i]);
        }

        // Output average result formatted to two decimal places
        System.out.println("\nAverage result: " + String.format("%.2f", average));

        // Calculate and output the overall grade
        String grade = calculateGrade(average);
        System.out.println("Overall Grade: " + grade);

        // Output grade description
        System.out.println("Grade Description: " + getGradeDescription(grade));

        
        scanner.close();
    }

    // Helper method to calculate the grade based on average marks
    private static String calculateGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Helper method to get grade description
    private static String getGradeDescription(String grade) {
        switch (grade) {
            case "A":
                return "Excellent - You have achieved outstanding performance! Keep up the great work!";
            case "B":
                return "Very Good - You have performed well and are on the right track!";
            case "C":
                return "Satisfactory - Well Done this is a good result.";
            case "D":
                return "Passing - You have passed, but there is room for improvement. Put in more effort!";
            default:
                return "Fail - Additional effort is needed to pass.";
        }
    }
}
