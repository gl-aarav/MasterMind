/**
 *  The PegArray class represents an array of pegs, used for both the master code
 *  and player guesses in the MasterMind game. It also calculates exact and partial matches.
 *
 *  @author  Aarav Goyal
 *  @since   September 26, 2025
 */
public class PegArray {
	private Peg[] pegs;
	private int exactMatches;
	private int partialMatches;

	/**
	 *  Constructs a PegArray with a specified number of pegs.
	 *  Initializes each peg in the array to a default 'X' letter.
	 *  @param numberOfPegs The number of pegs in this array.
	 */
	public PegArray(int numberOfPegs) {
		this.pegs = new Peg[numberOfPegs];

		for (int pegIndex = 0; pegIndex < this.pegs.length; pegIndex++) {
			this.pegs[pegIndex] = new Peg();
		}

	}

	/**
	 *  Retrieves a peg at the specified index.
	 *  @param index The index of the peg to retrieve.
	 *  @return The Peg object at the given index.
	 */
	public Peg getPeg(int index) {
		return this.pegs[index];
	}

	/**
	 *  Calculates the number of exact matches between this PegArray and another PegArray.
	 *  An exact match occurs when a peg has the same letter and is at the same position.
	 *  @param otherPegArray The other PegArray to compare against.
	 *  @return The count of exact matches.
	 */
	public int getExactMatches(PegArray otherPegArray) {
		this.exactMatches = 0;

		for (int pegIndex = 0; pegIndex < this.pegs.length; pegIndex++) {
			if (this.pegs[pegIndex].getLetter() == otherPegArray.getPeg(pegIndex).getLetter()) {
				this.exactMatches++;
			}
		}

		return this.exactMatches;
	}

	/**
	 *  Calculates the number of partial matches between this PegArray and another PegArray.
	 *  A partial match occurs when a peg has the same letter but is at a different position,
	 *  and has not already been counted as an exact or partial match.
	 *  @param otherPegArray The other PegArray to compare against.
	 *  @return The count of partial matches.
	 */
	public int getPartialMatches(PegArray otherPegArray) {
		boolean[] thisPegMatched = new boolean[this.pegs.length];
		boolean[] otherPegMatched = new boolean[this.pegs.length];

		int pegIndex;
		for (pegIndex = 0; pegIndex < thisPegMatched.length; pegIndex++) {
			if (this.pegs[pegIndex].getLetter() == otherPegArray.getPeg(pegIndex).getLetter()) {
				thisPegMatched[pegIndex] = false;
				otherPegMatched[pegIndex] = false;
			} else {
				thisPegMatched[pegIndex] = true;
				otherPegMatched[pegIndex] = true;
			}
		}

		this.partialMatches = 0;

		for (pegIndex = 0; pegIndex < otherPegMatched.length; pegIndex++) {
			if (otherPegMatched[pegIndex]) {
				int innerPegIndex = 0;

				for (boolean foundPartialMatch = false; innerPegIndex < thisPegMatched.length && !foundPartialMatch; innerPegIndex++) {
					if (thisPegMatched[innerPegIndex] && this.pegs[pegIndex].getLetter() == otherPegArray.getPeg(innerPegIndex).getLetter()) {
						this.partialMatches++;
						thisPegMatched[innerPegIndex] = false;
						otherPegMatched[pegIndex] = false;
						foundPartialMatch = true;
					}
				}
			}
		}

		return this.partialMatches;
	}

	/**
	 *  Returns the number of exact matches found in the last comparison.
	 *  @return The count of exact matches.
	 */
	public int getExact() {
		return this.exactMatches;
	}

	/**
	 *  Returns the number of partial matches found in the last comparison.
	 *  @return The count of partial matches.
	 */
	public int getPartial() {
		return this.partialMatches;
	}
}
