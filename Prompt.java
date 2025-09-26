import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 *	Prompt.java - Uses BufferedReader.
 *	Provides utilities for user input.  This enhances the BufferedReader
 *	class so our programs can recover from "bad" input, and also provides
 *	a way to limit numerical input to a range of values.
 *
 *	The advantages of BufferedReader are speed, synchronization, and piping
 *	data in Linux.
 *
 *	@author	Aarav Goyal
 *	@since	09/05/2025
 */

public class Prompt
{
	// BufferedReader variables
	private static InputStreamReader streamReader;
	private static BufferedReader bufReader;

	public Prompt() {
	}

	/**
	 *	Prompts user for string of characters and returns the string.
	 *	@param ask  The prompt line
	 *	@return  	The string input
	 */
	public static String getString (String ask)
	{
		System.out.print(ask + " -> ");
		String input = "";
		try {
			input = bufReader.readLine();
		}
		catch (IOException e) {
			System.err.println("ERROR: Buffered Reader could not read line");
		}
		return input;
	}
	
	/**
	 *	Prompts the user for a character and returns the character.
	 *	@param ask  The prompt line
	 *	@return  	The character input
	 */
	public static char getChar(String var0) {
		new String("");

		String var1;
		do {
			var1 = getString(var0);
		} while(var1.length() != 1);

		char var3 = var1.charAt(0);
		return var3;
	}
	
	/**
	 *	Prompts the user for an integer and returns the integer.
	 *	@param ask  The prompt line
	 *	@return  	The integer input
	 */
	public static int getInt (String ask)
	{
		int val = 0;
		boolean found = false;
		while (!found) {
			String str = getString(ask);
			try {
				val = Integer.parseInt(str);
				found = true;
			}
			catch (NumberFormatException e) {
				found = false;
			}
		}
		
		return val;
	}
	
	/**
	 *	Prompts the user for an integer using a range of min to max,
	 *	and returns the integer.
	 *	@param ask  The prompt line
	 *	@param min  The minimum integer accepted
	 *	@param max  The maximum integer accepted
	 *	@return  	The integer input
	 */
	public static int getInt (String ask, int min, int max)
	{


		int var4;
		do {
			do {
				var4 = getInt(ask + " (" + min + " - " + max + ")");
			} while(var4 < min);
		} while(var4 > max);

		return var4;
	}
	
	/**
	 *	Prompts the user for a double and returns the double.
	 *	@param ask  The prompt line
	 *	@return  The double input
	 */
	public static double getDouble (String ask)
	{
		boolean var1 = false;
		new String("");
		double var3 = 0.0;

		do {
			var1 = false;
			String var2 = getString(ask);

			try {
				var3 = Double.parseDouble(var2);
			} catch (NumberFormatException var6) {
				var1 = true;
			}
		} while(var1);

		return var3;
	}
	
	/**
	 *	Prompts the user for a double and returns the double.
	 *	@param ask  The prompt line
	 *	@param min  The minimum double accepted
	 *	@param max  The maximum double accepted
	 *	@return  The double input
	 */
	public static double getDouble (String ask, double min, double max)
	{
		double var5 = 0.0;
		String var7 = String.format("%.2f", min);
		String var8 = String.format("%.2f", max);

		do {
			do {
				var5 = getDouble(ask + " (" + var7 + " - " + var8 + ")");
			} while(var5 < min);
		} while(var5 > max);

		return var5;
	}

	static {
		streamReader = new InputStreamReader(System.in);
		bufReader = new BufferedReader(streamReader);
	}
}
