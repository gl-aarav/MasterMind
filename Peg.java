/**
 *  The Peg class represents a single peg in the MasterMind game.
 *  Each peg has a letter from 'A' to 'F'.
 *
 *  @author  Aarav Goyal
 *  @since   September 26, 2025
 */
public class Peg {
	private char letter;

	/**
	 *  Default constructor. Initializes the peg with a default letter 'X'.
	 */
	public Peg() {
		this.letter = 'X';
	}

	/**
	 *  Constructor that initializes the peg with a specified letter.
	 *  @param inputLetter The character to set for the peg.
	 */
	public Peg(char inputLetter) {
		this.letter = inputLetter;
	}

	/**
	 *  Retrieves the letter of this peg.
	 *  @return The character representing the peg's letter.
	 */
	public char getLetter() {
		return this.letter;
	}

	/**
	 *  Sets the letter of this peg.
	 *  @param inputLetter The new character to set for the peg.
	 */
	public void setLetter(char inputLetter) {
		this.letter = inputLetter;
	}
}
