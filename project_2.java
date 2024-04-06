import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class project_2 {
    
    private static final String FILE_PATH = "P1input.txt";

    /**
     * The main method that reads the input file, creates a puzzle GUI, and starts the game.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        TextFileInput in = new TextFileInput(FILE_PATH);
        String letters = readLetters(in);
        UnsortedWordList unsorted = new UnsortedWordList();
        SortedWordList sorted = new SortedWordList();
        String[] solutions = readSolutions(in, unsorted, sorted);
        PuzzleGuis puzzleGUI = new PuzzleGuis(letters, solutions);
        puzzleGUI.startGame();
        playGame(puzzleGUI, letters, solutions);
        
    }
    
    /**
     * Reads the letters from the input file.
     *
     * @param in The TextFileInput object for reading the file.
     * @return The letters read from the file.
     */
    private static String readLetters(TextFileInput in) {
        return in.readLine();
    }

    /**
     * Reads the solutions from the input file.
     *
     * @param in The TextFileInput object for reading the file.
     * @return The solutions read from the file.
     */
    private static String[] readSolutions(TextFileInput in, UnsortedWordList unsorted, SortedWordList sorted) {
        List<String> solutionsList = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null) {
            Word node = new Word(line);
            unsorted.add(node);
            sorted.add(node);
            System.out.println(unsorted);
            solutionsList.add(line);
        }
        return solutionsList.toArray(new String[0]);
    }

    /**
     * Checks if a guess is valid based on the given letters.
     *
     * @param guess The guess to check.
     * @param letters The letters available for the guess.
     * @return True if the guess is valid, false otherwise.
     */
    private static boolean isValidGuess(String guess, String letters) {
        for (char c : guess.toCharArray()) {
            if (letters.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Displays an error message in a dialog box.
     *
     * @param message The message to display.
     */
    private static void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Plays the word puzzle game, prompting the user for guesses and updating the GUI.
     *
     * @param puzzleGuis The PuzzleGuis object representing the game interface.
     * @param letters The letters available for the game.
     * @param solutions The solutions to the puzzle.
     */
    private static void playGame(PuzzleGuis puzzleGuis, String letters, String[] solutions) {
        int score = 0;
        while (score < solutions.length) {
            String input = JOptionPane.showInputDialog("Enter your guess:");
            if (input == null) {
                System.exit(0); // User clicked cancel, exits the game and shuts down the program
            }
            if (input.length() < 5) { // Check if the guess is less than 5 letters long
                displayErrorMessage("Your guess is less than 5 letters long!");
            } else if (!isValidGuess(input, letters)) { 
                displayErrorMessage("Your guess contains letters that are not in the given set!");
            } else if (!containsIgnoreCase(solutions, input)) { // Check if the guess is in the solutions list
                displayErrorMessage("Your guess is not in the solutions list.");
            } else {
                score++;
                puzzleGuis.addGuessedWord(input);
                puzzleGuis.increaseScore(); // Increase the score in the GUI
                JOptionPane.showMessageDialog(null, "Your guess is correct!");
            }
        }
        if (score == solutions.length) { // Displays victory message if score is equal to total amount of solutions.
            JOptionPane.showMessageDialog(null, "Congratulations! You have guessed all possible solutions.");
        }
    }

    /**
     * Checks if a word is in the list of solutions, ignoring case.
     *
     * @param words The list of solutions.
     * @param input 
     * @return 
     */
    private static boolean containsIgnoreCase(String[] words, String input) {
        for (String word : words) { 
            if (word.equalsIgnoreCase(input)) { 
                return true;
            }
        }
        return false;
    }
}
/*


* Reading Input:

The readLetters method reads the first line of the input file, which contains the letters available for the puzzle.
The readSolutions method reads the remaining lines of the input file, which represent the solutions to the puzzle. It stores the solutions in a String array.
Validation:

The isValidGuess method checks if a guess is valid based on the given letters. It ensures that the guess contains only letters from the available set.
The containsIgnoreCase method checks if a word is in the list of solutions, ignoring case. This ensures that the game is not case-sensitive when evaluating guesses against solutions.
Error Handling:

The displayErrorMessage method displays an error message in a dialog box using JOptionPane.
Game Logic:

The playGame method controls the flow of the game. It prompts the user for guesses and updates the GUI accordingly.
Inside the playGame method, the program continuously prompts the user for guesses until all solutions are found. For each guess:
It checks if the guess is at least 5 letters long, if it contains letters not in the given set, and if it is in the solutions list.
If the guess is valid, the score is increased, and the guessed word is added to the GUI.
When all solutions are found, a victory message is displayed.
GUI Interaction :The PuzzleGuis class represents the GUI for the puzzle game. It interacts with the user by displaying the letters, guessed words, and score.
 */