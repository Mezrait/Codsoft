import java.util.Scanner;
public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0; // Keep track of player's score

        System.out.println("🎮 Welcome to the Number Guessing Game! 🎉");
        System.out.println("Guess the number between 1 and 100. You have 5 attempts per round.");
        System.out.println("Let's get started!");

        boolean playAgain = true; // Flag to allow multiple rounds
        while (playAgain) {
            int targetNumber = (int) (Math.random() * 100) + 1; // Generate a random number
            int attempts = 7; // Set the total number of attempts
            boolean guessedCorrectly = false; // Track if the user guesses correctly

            System.out.println("\n🤔 A new number has been generated! Can you guess it?");
            System.out.println("🔢 You have " + attempts + " attempts. Good luck!");

            // Game loop for guessing
            while (attempts > 0) {
                System.out.print("💡 Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();
                attempts--; // Deduct an attempt

                // Check the guess
                if (userGuess == targetNumber) {
                    System.out.println("🎉 Correct! You guessed the number! 🎯");
                    score++; // Increase the score
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("📉 Too low! Try again.");
                } else {
                    System.out.println("📈 Too high! Try again.");
                }

                // Show remaining attempts or end the round
                if (attempts > 0) {
                    System.out.println("🔄 You have " + attempts + " attempts left. Keep going!");
                } else {
                    System.out.println("❌ Out of attempts! The correct number was " + targetNumber + ".");
                }
            }

            // Ask if the user wants to play again
            System.out.print("\n🔁 Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        // End the game and display the score
        System.out.println("\n🏁 Game Over! Your final score is: " + score);
        System.out.println("Thank you for playing! 🎉 See you next time! 👋");
        scanner.close();
    }
}


