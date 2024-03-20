package edu.depaul;

public class Validate_Input {
	public static boolean val(String input, int type) {
		
		if (type == 0) {
			int integers = -1;
			try {
				integers = Integer.parseInt(input);

			} catch (NumberFormatException e) {
				if (input.equalsIgnoreCase("escape")) {
					System.out.println("Input Aborted");
					return false;
				}
				System.out.print("--Must be Integer--\n");
			}
			if (integers != -1) {
				return true;
			}
			return false;
		}
		
		if (type == 1) {
			long longs = -1;
			try {
				longs = Long.parseLong(input);

			} catch (NumberFormatException e) {
				if (input.equalsIgnoreCase("escape")) {
					System.out.println("Input Aborted");
					return false;
				}
				System.out.print("--Must be Long--\n");
			}
			if (longs != -1) {
				return true;
			}
			return false;
		}
		return false;
	}
}
