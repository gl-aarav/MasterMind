public class PegArray {
	private Peg[] pegs;
	private int exactMatches;
	private int partialMatches;

	public PegArray(int var1) {
		this.pegs = new Peg[var1];

		for (int var2 = 0; var2 < this.pegs.length; ++var2) {
			this.pegs[var2] = new Peg();
		}

	}

	public Peg getPeg(int var1) {
		return this.pegs[var1];
	}

	public int getExactMatches(PegArray var1) {
		this.exactMatches = 0;

		for (int var2 = 0; var2 < this.pegs.length; ++var2) {
			if (this.pegs[var2].getLetter() == var1.getPeg(var2).getLetter()) {
				++this.exactMatches;
			}
		}

		return this.exactMatches;
	}

	public int getPartialMatches(PegArray var1) {
		boolean[] var2 = new boolean[this.pegs.length];
		boolean[] var3 = new boolean[this.pegs.length];

		int var4;
		for (var4 = 0; var4 < var2.length; ++var4) {
			if (this.pegs[var4].getLetter() == var1.getPeg(var4).getLetter()) {
				var2[var4] = false;
				var3[var4] = false;
			} else {
				var2[var4] = true;
				var3[var4] = true;
			}
		}

		this.partialMatches = 0;

		for (var4 = 0; var4 < var3.length; ++var4) {
			if (var3[var4]) {
				int var5 = 0;

				for (boolean var6 = false; var5 < var2.length && !var6; ++var5) {
					if (var2[var5] && this.pegs[var4].getLetter() == var1.getPeg(var5).getLetter()) {
						++this.partialMatches;
						var2[var5] = false;
						var3[var4] = false;
						var6 = true;
					}
				}
			}
		}

		return this.partialMatches;
	}

	public int getExact() {
		return this.exactMatches;
	}

	public int getPartial() {
		return this.partialMatches;
	}
}
