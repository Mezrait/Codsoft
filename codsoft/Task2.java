import java.util.Scanner;
public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to input the number of subjects
        System.out.print("✨ Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Create an array to store marks for each subject
        int[] marks = new int[numSubjects];

        // Collect marks for each subject from the user
        System.out.println("✏️ Enter the marks obtained in each subject (out of 100):");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("📖 Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        // Initialize total marks to zero
        int totalMarks = 0;

        // Calculate the total marks by summing up individual marks
        for (int mark : marks) {
            totalMarks += mark;
        }

        // Calculate the average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Assign a grade based on the average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display the results in a user-friendly format
        System.out.println("\n✨ Results:");
        System.out.println("────────────────────────────────────");
        System.out.println("📊 Total Marks: " + totalMarks);
        System.out.println("🌟 Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        System.out.println("🎓 Grade: " + grade);
        System.out.println("────────────────────────────────────");

        // Close the scanner to release resources
        scanner.close();
    }
}


