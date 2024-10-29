import java.util.Random; // Import the Random class for generating random numbers
import java.util.Scanner; // Import the Scanner class for user input

public class GuessTheNumberGame {
    private int randomNumber; // The random number to guess
    private int maxAttempts;   // The maximum number of attempts allowed
    private int attemptsMade;  // The number of attempts made by the player
    private int bestScore;     // The best score achieved (minimum attempts)

    // Constructor to initialize the game
    public GuessTheNumberGame(int maxAttempts) {
        this.maxAttempts = maxAttempts; // Set the maximum attempts
        this.randomNumber = generateRandomNumber(1, 100); // Generate random number between 1 and 100
        this.attemptsMade = 0; // Initialize attempts made to 0
        this.bestScore = Integer.MAX_VALUE; // Set initial best score to the maximum value
    }

    // Method to generate a random number within a specified range
    private int generateRandomNumber(int min, int max) {
        Random random = new Random(); // Create a Random object
        return random.nextInt(max - min + 1) + min; // Generate a random number inclusive of both min and max
    }

    // Method to start the game
    public void startGame() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input
        System.out.println("Welcome to the Guess the Number Game!"); // Welcome message
        System.out.println("I have selected a number between 1 and 100. You have " + maxAttempts + " attempts to guess it."); // Instructions

        while (attemptsMade < maxAttempts) { // Loop until the maximum attempts are reached
            System.out.print("Enter your guess: "); // Prompt user for input
            int playerGuess = getPlayerGuess(scanner); // Get and validate player guess
            attemptsMade++; // Increment the number of attempts made

            // Provide feedback based on the player's guess
            if (playerGuess < randomNumber) {
                System.out.println("Too low! Try again."); // Guess is too low
            } else if (playerGuess > randomNumber) {
                System.out.println("Too high! Try again."); // Guess is too high
            } else {
                System.out.println("Congratulations! You've guessed the number " + randomNumber + " in " + attemptsMade + " attempts!"); // Correct guess
                updateBestScore(attemptsMade); // Update the best score if necessary
                return; // Exit the game if the guess is correct
            }
        }

        // If the player runs out of attempts
        System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber + "."); // Reveal the correct number
    }

    // Method to get and validate the player's guess
    private int getPlayerGuess(Scanner scanner) {
        while (true) { // Infinite loop until a valid guess is provided
            if (scanner.hasNextInt()) { // Check if the next input is an integer
                int guess = scanner.nextInt(); // Read the integer input
                if (guess >= 1 && guess <= 100) { // Validate the range
                    return guess; // Return the valid guess
                } else {
                    System.out.println("Please enter a number between 1 and 100."); // Invalid range
                }
            } else {
                System.out.println("That's not a valid number! Please enter a number between 1 and 100."); // Invalid input
                scanner.next(); // Clear the invalid input
            }
        }
    }

    // Method to update the best score if the current attempt count is lower
    private void updateBestScore(int attempts) {
        if (attempts < bestScore) { // Check if the current attempts are a new best
            bestScore = attempts; // Update the best score
            System.out.println("New best score: " + bestScore + " attempts!"); // Inform the user of their new best score
        }
    }

    // Method to display the best score
    public void displayBestScore() {
        if (bestScore != Integer.MAX_VALUE) { // Check if the best score has been set
            System.out.println("Your best score is: " + bestScore + " attempts."); // Display the best score
        } else {
            System.out.println("You have not set a best score yet."); // No best score yet
        }
    }

    // Method to ask the player if they want to play again
    public boolean playAgain() {
        Scanner scanner = new Scanner(System.in); // Create a new Scanner object
        System.out.print("Would you like to play again? (yes/no): "); // Prompt for replay
        String response = scanner.nextLine(); // Read user response
        return response.equalsIgnoreCase("yes"); // Return true if the user wants to play again
    }

    // Main method to run the game
    public static void main(String[] args) {
        do {
            GuessTheNumberGame game = new GuessTheNumberGame(5); // Set maximum attempts to 5
            game.startGame(); // Start the game
            game.displayBestScore(); // Display the best score after each game

        } while (new GuessTheNumberGame(5).playAgain()); // Repeat if the player wants to play again
    }
}