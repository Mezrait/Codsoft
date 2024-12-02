import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
public class Task4 {
    private static int score = 0; // To keep track of the user's score
    private static boolean answered = false; // Flag to check if a question has been answered
    private static boolean timedOut = false; // Flag to check if time ran out
    private static final Object lock = new Object(); // Dedicated lock object for synchronization

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcoming message
        System.out.println("🎉 Welcome to the Quiz! Let's test your knowledge!");
        System.out.println("⏳ You have 10 seconds to answer each question. Let's begin!\n");

        // Quiz questions and answers
        String[] questions = {
                "🌍 What is the capital of France?",
                "🪐 Which planet is known as the Red Planet?",
                "🐋 What is the largest mammal on Earth?",
                "🔢 What is 9 + 10?",
                "💻 Who is known as the father of computers?"
        };

        String[][] options = {
                {"1. 🏙️ Berlin", "2. 🏙️ Madrid", "3. 🏙️ Paris", "4. 🏙️ Rome"},
                {"1. 🌍 Earth", "2. 🔴 Mars", "3. 🌀 Jupiter", "4. 🌕 Venus"},
                {"1. 🐘 Elephant", "2. 🐋 Blue Whale", "3. 🦒 Giraffe", "4. 🦈 Shark"},
                {"1. 18", "2. 19", "3. 21", "4. 20"},
                {"1. Charles Darwin", "2. Charles Babbage", "3. Alan Turing", "4. Ada Lovelace"}
        };

        int[] correctAnswers = {3, 2, 2, 2, 2}; // Correct option numbers

        // Loop through questions
        for (int i = 0; i < questions.length; i++) {
            answered = false; // Reset the answered flag for each question
            timedOut = false; // Reset the timed-out flag for each question
            askQuestion(questions[i], options[i], correctAnswers[i], scanner);
        }

        // Display the final result
        System.out.println("\n🎉 Quiz Over!");
        System.out.println("🏅 Your final score is: " + score + "/" + questions.length);
        System.out.println("Thank you for playing! Have a great day ahead! 😊");

        scanner.close();
    }

    // Method to display a question and handle user response
    private static void askQuestion(String question, String[] options, int correctAnswer, Scanner scanner) {
        System.out.println("\n" + question);
        for (String option : options) {
            System.out.println(option);
        }

        Timer timer = new Timer(); // Timer to handle the 10-second limit
        TimerTask task = new TimerTask() {
            public void run() {
                synchronized (lock) {
                    if (!answered) {
                        System.out.println("\n⏰ Time's up! Moving to the next question.");
                        timedOut = true; // Set timed-out flag
                        answered = true; // Mark as answered to exit the loop
                    }
                }
            }
        };

        timer.schedule(task, 10000); // Schedule the timer for 10 seconds

        while (true) { // Loop to handle user input
            synchronized (lock) {
                if (answered) { // Break the loop if answered or timedOut is true
                    break;
                }
            }

            System.out.print("💡 Your answer (1-4): ");
            if (scanner.hasNextInt()) {
                int answer = scanner.nextInt();
                if (answer >= 1 && answer <= 4) {
                    synchronized (lock) {
                        if (!answered) { // Double-check to avoid race conditions
                            answered = true; // Mark as answered
                            timer.cancel(); // Cancel the timer immediately
                            if (answer == correctAnswer) {
                                System.out.println("✅ Correct!");
                                score++;
                            } else {
                                System.out.println("❌ Wrong! The correct answer was " + correctAnswer + ".");
                            }
                        }
                    }
                } else {
                    System.out.println("⚠️ Invalid input. Please select a number between 1 and 4.");
                }
            } else {
                System.out.println("⚠️ Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }

            synchronized (lock) {
                if (timedOut) { // Break the loop if timedOut is true
                    break;
                }
            }
        }

        timer.cancel(); // Ensure the timer is canceled
    }
}

