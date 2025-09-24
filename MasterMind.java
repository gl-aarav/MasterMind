public class MasterMind {
	private boolean reveal = false;
	private PegArray[] guesses = new PegArray[10];
	private PegArray master;
	private final int PEGS_IN_CODE = 4;
	private final int MAX_GUESSES = 10;
	private final int PEG_LETTERS = 6;
	private final boolean DEBUG = false;

	public MasterMind() {
		for (int var1 = 0; var1 < this.guesses.length; ++var1) {
			this.guesses[var1] = new PegArray(4);
		}

		this.master = new PegArray(4);
	}

	public static void main(String[] var0) {
		MasterMind var1 = new MasterMind();
		var1.run();
	}

	public void run() {
		this.printIntroduction();
		this.setMaster();
		this.playGame();
	}

	public void setMaster() {
		for (int var1 = 0; var1 < 4; ++var1) {
			char var2 = (char) ((int) (Math.random() * 6.0) + 65);
			this.master.getPeg(var1).setLetter(var2);
		}

	}

	public void playGame() {
		Prompt.getString("Hit the Enter key to start the game");
		int var1 = 0;
		boolean var2 = false;

		int var5;
		do {
			this.printBoard();
			System.out.println("\nGuess " + (var1 + 1) + "\n");
			String var3 = this.getGuess();

			for (int var4 = 0; var4 < 4; ++var4) {
				this.guesses[var1].getPeg(var4).setLetter(var3.charAt(var4));
			}

			var5 = this.guesses[var1].getExactMatches(this.master);
			this.guesses[var1].getPartialMatches(this.master);
			++var1;
		} while (var5 < 4 && var1 < 10);

		this.reveal = true;
		this.printBoard();
		if (var5 < 4) {
			System.out.println("Oops. You were unable to find the solution in 10 guesses.");
		} else {
			System.out.println("\nNice work! You found the master code in " + var1 + " guesses.\n");
		}

	}

	public String getGuess() {
		boolean var1 = true;
		String var2 = "";

		do {
			var2 = Prompt.getString("Enter the code using (A,B,C,D,E,F). For example, ABCD or abcd from left-to-right");
			var2 = var2.toUpperCase();
			var1 = true;
			if (var2.length() != 4) {
				var1 = false;
			}

			for (int var3 = 0; var3 < var2.length(); ++var3) {
				char var4 = var2.charAt(var3);
				if (var4 < 'A' || var4 > 'F') {
					var1 = false;
				}
			}

			if (!var1) {
				System.out.println("ERROR: Bad input, try again.");
			}
		} while (!var1);

		return var2;
	}

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

	public void printBoard() {
		System.out.print("+--------+");

		int var1;
		for (var1 = 0; var1 < 4; ++var1) {
			System.out.print("-------+");
		}

		System.out.println("---------------+");
		System.out.print("| MASTER |");

		for (var1 = 0; var1 < 4; ++var1) {
			if (this.reveal) {
				System.out.printf("   %c   |", this.master.getPeg(var1).getLetter());
			} else {
				System.out.print("  ***  |");
			}
		}

		System.out.println(" Exact Partial |");
		System.out.print("|        +");

		for (var1 = 0; var1 < 4; ++var1) {
			System.out.print("-------+");
		}

		System.out.println("               |");
		System.out.print("| GUESS  +");

		for (var1 = 0; var1 < 4; ++var1) {
			System.out.print("-------+");
		}

		System.out.println("---------------|");

		for (var1 = 0; var1 < 9; ++var1) {
			this.printGuess(var1);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}

		this.printGuess(9);
		System.out.print("+--------+");

		for (var1 = 0; var1 < 4; ++var1) {
			System.out.print("-------+");
		}

		System.out.println("---------------+");
	}

	public void printGuess(int var1) {
		System.out.printf("|   %2d   |", var1 + 1);
		char var2 = this.guesses[var1].getPeg(0).getLetter();
		int var3;
		if (var2 >= 'A' && var2 <= 'F') {
			for (var3 = 0; var3 < 4; ++var3) {
				System.out.print("   " + this.guesses[var1].getPeg(var3).getLetter() + "   |");
			}
		} else {
			for (var3 = 0; var3 < 4; ++var3) {
				System.out.print("       |");
			}
		}

		System.out.printf("   %d      %d    |\n", this.guesses[var1].getExact(), this.guesses[var1].getPartial());
	}
}
