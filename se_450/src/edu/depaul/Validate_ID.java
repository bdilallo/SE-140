package edu.depaul;

public class Validate_ID {
	public static boolean val(int input, int size) {
		if (input >= size || input < 0) {
			System.out.println("No Such ID");
			return false;
		}
		return true;
	}
}
