/**
 * This program implements the MasterMind game.
 *
 * @author Aarav Goyal
 * @since September 26, 2025
 */
public class MasterMind_V1 {
	private final int PEGS_IN_CODE; // Number of pegs in the MasterMind code
	private final int MAX_GUESSES; // Maximum number of guesses allowed
	private final int PEG_LETTERS; // Number of possible letters (A-F)
	private PegArray[] guesses; // Array to store player's guesses
	private PegArray master; // The randomly generated master code
	private boolean masterRevealed; // whether to show the master code on the board

	/**
	 * Constructs a MasterMind game instance.
	 * Initializes the player guesses array and the master code.
	 */
	public MasterMind_V1() {
		PEGS_IN_CODE = 4; // Number of pegs in the code
		MAX_GUESSES = 10; // Maximum number of guesses allowed
		PEG_LETTERS = 6; // Letters A-F
		guesses = new PegArray[MAX_GUESSES];
		// Initialize each element of the guesses array
		for (int guessIndex = 0; guessIndex < guesses.length; guessIndex++) {
			guesses[guessIndex] = new PegArray(PEGS_IN_CODE);
		}

		// Initialize the master code
		master = new PegArray(PEGS_IN_CODE);
		this.masterRevealed = false;
	}

	/**
	 * Main method to start the MasterMind game.
	 * 
	 * @param args Command line arguments (not used).
	 */
	public static void main(String[] args) {
		MasterMind_V1 game = new MasterMind_V1();
		game.run();
	}

	/**
	 * Runs the MasterMind game sequence.
	 * Includes printing the introduction, setting the master code, and playing the
	 * game.
	 */
	public void run() {
		this.printIntroduction();
		this.setMaster();
		this.playGame();
	}

	/**
	 * Sets a random master code for the game.
	 * The code consists of PEGS_IN_CODE pegs, each with a random letter from 'A' to
	 * 'F'.
	 */
	public void setMaster() {
		// Generate a random letter for each peg in the master code
		for (int pegIndex = 0; pegIndex < PEGS_IN_CODE; pegIndex++) {
			char randomLetter = (char) ((int) (Math.random() * PEG_LETTERS) + 'A');
			this.master.getPeg(pegIndex).setLetter(randomLetter);
		}

	}

	/**
	 * Manages the main gameplay loop.
	 * Players make guesses, and exact and partial matches are reported.
	 * The game ends when the master code is guessed or max guesses are reached.
	 */
	public void playGame() {
		Prompt.getString("Hit the Enter key to start the game");
		int currentGuessNumber = 0;

		int exactMatchesInCurrentGuess;
		do {
			this.printBoard();
			System.out.println("\nGuess " + (currentGuessNumber + 1) + "\n");
			String playerGuess = this.getGuess();

			// Set the letters of the current guess based on player input
			for (int pegIndex = 0; pegIndex < PEGS_IN_CODE; pegIndex++) {
				this.guesses[currentGuessNumber].getPeg(pegIndex).setLetter(playerGuess.charAt(pegIndex));
			}

			// Calculate exact and partial matches for the current guess
			exactMatchesInCurrentGuess = this.guesses[currentGuessNumber]
					.getExactMatches(this.master);
			this.guesses[currentGuessNumber].getPartialMatches(this.master);
			currentGuessNumber++;
		} while (exactMatchesInCurrentGuess < PEGS_IN_CODE && currentGuessNumber < MAX_GUESSES);

		// Reveal the master code at the end of the game
		this.masterRevealed = true;
		this.printBoard();
		// Determine if the player won or lost and print appropriate message
		if (exactMatchesInCurrentGuess < PEGS_IN_CODE) {
			System.out.println("Oops. You were unable to find the solution in "
					+ MAX_GUESSES + " guesses.");
		} else {
			System.out.println("\nNice work! You found the master code in "
					+ currentGuessNumber + " guesses.\n");
		}

	}

	/**
	 * Prompts the user for a guess and validates the input.
	 * Ensures the guess is 4 characters long and consists of letters from 'A' to
	 * 'F'.
	 * 
	 * @return The validated player's guess as an uppercase string.
	 */
	public String getGuess() {
		boolean isValidInput = true;
		String guessString = "";

		do {
			// Get input from the user
			guessString = Prompt.getString("Enter the code using (A,B,C,D,E,F). "
					+ "For example, ABCD or abcd from left-to-right");
			guessString = guessString.toUpperCase();
			isValidInput = true;
			// Validate guess length
			if (guessString.length() != PEGS_IN_CODE) {
				isValidInput = false;
			}

			// Validate each character in the guess
			for (int charIndex = 0; charIndex < guessString.length(); charIndex++) {
				char currentCharacter = guessString.charAt(charIndex);
				if (currentCharacter < 'A' || currentCharacter > 'F') {
					isValidInput = false;
				}
			}

			// Print error message if input is invalid
			if (!isValidInput) {
				System.out.println("ERROR: Bad input, try again.");
			}
		} while (!isValidInput);

		return guessString;
	}

	/**
	 * Prints the introduction and rules of the MasterMind game to the console.
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| ___  ___             _              ___  ___ _             _                       |");
		System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
		System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
		System.out
				.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
		System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
		System.out.println(
				"| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
		System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
		System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
		System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
		System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
		System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
		System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
		System.out.println("| a loss.                                                                            |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}

	/**
	 * Displays the current state of the game board.
	 * Shows the master code (if revealed), player guesses, and match results.
	 */
	public void printBoard() {
		System.out.print("+--------+"); // Top border of the board

		// Print separators for pegs
		int boardLineIndex;
		for (boardLineIndex = 0; boardLineIndex < PEGS_IN_CODE; boardLineIndex++) {
			System.out.print("-------+");
		}

		System.out.println("---------------+");
		System.out.print("| MASTER |"); // Master code label

		// Display master code pegs or asterisks if not revealed
		for (boardLineIndex = 0; boardLineIndex < PEGS_IN_CODE; boardLineIndex++) {
			if (this.masterRevealed) {
				System.out.printf("   %c   |", this.master.getPeg(boardLineIndex).getLetter());
			} else {
				System.out.print("   *   |");
			}
		}

		System.out.println(" Exact Partial |");
		System.out.print("|        +"); // Separator line

		// Print separators for pegs
		for (boardLineIndex = 0; boardLineIndex < PEGS_IN_CODE; boardLineIndex++) {
			System.out.print("-------+");
		}

		System.out.println("               |");
		System.out.print("| GUESS  +"); // Guess label

		// Print separators for pegs
		for (boardLineIndex = 0; boardLineIndex < PEGS_IN_CODE; boardLineIndex++) {
			System.out.print("-------+");
		}

		System.out.println("---------------|");

		// Display all guesses
		for (boardLineIndex = 0; boardLineIndex < MAX_GUESSES - 1; boardLineIndex++) {
			this.printGuess(boardLineIndex);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}

		// Display the last guess
		this.printGuess(MAX_GUESSES - 1);
		System.out.print("+--------+"); // Bottom border of the board

		// Print separators for pegs
		for (boardLineIndex = 0; boardLineIndex < PEGS_IN_CODE; boardLineIndex++) {
			System.out.print("-------+");
		}

		System.out.println("---------------+");
	}

	/**
	 * Displays a single guess row on the game board.
	 * Shows the guess number, the pegs in the guess, and the exact and partial
	 * matches.
	 * 
	 * @param guessNumber The index of the guess to display (0-based).
	 */
	public void printGuess(int guessNumber) {
		System.out.printf("|   %2d   |", guessNumber + 1);
		char firstPegLetter = this.guesses[guessNumber].getPeg(0).getLetter();
		int pegIndex;
		// Check if the guess has been made (first peg is not default 'X')
		if (firstPegLetter >= 'A' && firstPegLetter <= 'F') {
			for (pegIndex = 0; pegIndex < PEGS_IN_CODE; pegIndex++) {
				System.out.print("   " + this.guesses[guessNumber].getPeg(pegIndex).getLetter() + "   |");
			}
		} else {
			// Display empty pegs if no guess has been made for this row
			for (pegIndex = 0; pegIndex < PEGS_IN_CODE; pegIndex++) {
				System.out.print("       |");
			}
		}

		System.out.printf("   %d      %d    |\n", this.guesses[guessNumber].getExact(),
				this.guesses[guessNumber].getPartial());
	}
}