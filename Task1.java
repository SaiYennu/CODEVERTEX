import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int secretNumber, guess, attempts, roundsPlayed, roundsWon;
        boolean playAgain;

        roundsPlayed = roundsWon = 0;
        playAgain = true;

        while (playAgain) {
            roundsPlayed++;

            // Generate a random number between 1 and 100
            secretNumber = (int) (Math.random() * 100) + 1;
            attempts = 0;
            
            System.out.println("I'm thinking of a number between 1 and 100.");

            // Limit the number of attempts to 5
            while (attempts < 5) {
                attempts++;

                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();

                if (guess == secretNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    roundsWon++;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }
            }

            if (attempts == 5) {
                System.out.println("Sorry, you ran out of attempts. The number was " + secretNumber);
            }

            // Ask if the user wants to play again
            System.out.print("Do you want to play again? (y/n): ");
            playAgain = scanner.next().equalsIgnoreCase("y");
        }

        // Display the user's score
        System.out.println("You played " + roundsPlayed + " rounds and won " + roundsWon + ".");
    }
}