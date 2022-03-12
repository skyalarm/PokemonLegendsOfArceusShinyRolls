/* This is a program to simulate shiny encounters from the Pokemon game Legends of Arceus
   This program was written by Skyler Troi, master programmer and cyber security agent
   Do not redistribute without permission from Skyler Troi
*/
import java.util.Scanner;
import java.util.Random;

public class shiny
{
	// Gets the input and calculates the shiny odds
	public static void main(String[] args) {
	
		/* Generates a random number between 0 and 9 to be matched with
		   an index of swearPhrases if the user gives invalid input
		*/
		Random r = new Random();
		boolean isInt = true;
		int swearPhrase = r.nextInt(10);



		Scanner scanner = new Scanner(System.in, "US-ASCII");
		
		System.out.print("Enter how many rolls you want: ");
		
		String rollString = scanner.next();
		int rolls = -1;

		// Check if integer, and if not then swear at user and exit program
		if (!checkIfInt(rollString)) {
			terminate(swearPhrase);
		}

		else {
			rolls = Integer.parseInt(rollString);
		}

		// Invalid input, swear at user and terminate program
		if (rolls < 0) {
			terminate(swearPhrase);
		}

		System.out.print("Do you have the shiny charm, enter 1 for yes, 0 for no ");

		String shinyCharmString = scanner.next();
		int shinyCharm = -1;

		// Check if integer, and if not then swear at user and exit program
		if (!checkIfInt(shinyCharmString)) {
			terminate(swearPhrase);
		}

		else {
			shinyCharm = Integer.parseInt(shinyCharmString);
		}



		System.out.print("Is the Pokemon you're rolling for completed or perfected, enter 1 for yes, 0 for no ");
		
		String completeOrPerfectString = scanner.next();
		int completeOrPerfect = -1;


		// Check if integer, and if not then swear at user and exit program
		if (!checkIfInt(completeOrPerfectString)) {
			terminate(swearPhrase);
		}

		else {
			completeOrPerfect = Integer.parseInt(completeOrPerfectString);
		}

		int perfected = 0;
		if (completeOrPerfect == 1) {
			System.out.print("Is the Pokemon you're rolling for perfected, enter 1 for yes, 0 for no ");

			String perfectedString = scanner.next();

			// Check if integer, and if not then swear at user and exit program
			if (!checkIfInt(perfectedString)) {
				terminate(swearPhrase);
			}
	
			else {
				perfected = Integer.parseInt(perfectedString);
			}

		}
		
		// Invalid input, swear at user and terminate program
		if (completeOrPerfect != 1 && completeOrPerfect != 0) {
			terminate(swearPhrase);
		}
		
		int valueOfCompletion = -1;
		// completed, but not perfected
		if (completeOrPerfect == 1 && perfected == 0) {
			valueOfCompletion = 1;
		}
		
		// perfected
		else if (completeOrPerfect == 1 && perfected == 1) {
			valueOfCompletion = 2;
		}
		
		else if (completeOrPerfect == 0 && perfected == 0) {
			valueOfCompletion = 0;
		}

		// No other comboination poissible, terminate the program and swear at the user
		else {
			terminate(swearPhrase);
		}


		// Invalid combination, terminate the program and swear at the user
		if (valueOfCompletion == 0 && shinyCharm == 1) {
			terminate(swearPhrase);
		}

		System.out.print("Is the Pokemon you're rolling for in an outbreak, enter 1 for yes, 0 for no ");

		String outbreakValueString = scanner.next();
		int outbreakValue = -1;
		// Check if integer, and if not then swear at user and exit program
		if (!checkIfInt(outbreakValueString)) {
			terminate(swearPhrase);
		}

		else {
			outbreakValue = Integer.parseInt(outbreakValueString);
		}


		int baseChance = 0;
		
		boolean numeratorisOne = true;
		// Calculate base chance based off of user input
		

		// Calculate for non outbreaks
		if (outbreakValue == 0) {

			if (shinyCharm == 0) {

				if (valueOfCompletion == 0) {
					baseChance = 4096;
				}

				else if (valueOfCompletion == 1) {
					baseChance = 2048;
				}

				else {
					baseChance = 1024;
				}

	
			}



			else {

				if (valueOfCompletion == 1) {
					baseChance = 819;
				}

				else {
					baseChance = 585;
				}
			}
		}

		// Calculate for outbreaks
		else {

			if (shinyCharm == 0) {

				if (valueOfCompletion == 0) {
					baseChance = 158;
				}

				else if (valueOfCompletion == 1) {
					baseChance = 152;
				}

				else {
					baseChance = 141;
				}

	
			}



			else {

				if (valueOfCompletion == 1) {
					baseChance = 137;
				}

				else {

					numeratorisOne = false;
				}
			}
		}

		int numberOfShinies = rollForShinies(rolls, baseChance, numeratorisOne);


		if (numberOfShinies > 0) {
			if (numberOfShinies > 1) {
				System.out.println("Congratulations, you have found " + numberOfShinies + " shinies!");
			}
			else {
				System.out.println("Congratulations, you have found " + numberOfShinies + " shiny!");
			}
		}
		
		else {
			System.out.println("Congratulations! You haven't even found a single shiny!");
		}
	}
	
	public static int rollForShinies(int rolls, int baseChance, boolean numeratorisOne) {
		Random r = new Random();

		int numerator = 1;
		int denominator = baseChance;

		/* This case only happens if you have the shiny charm, perfect research and a mass outbreak.
		   This sets the chane to 1 / 128.49, which is a pain to deal with so we're treating it as
		   100 / 12849, which is statistically the same exact chance
		*/
		if (!numeratorisOne) {
			numerator = 100;
			denominator = 12849;
		}

		// Now loop through rolls times and generate a random number each time and check if it matches
		int shinyNumber = 0;
		int numShinies = 0;
		for (int i = 0; i < rolls; i++ ) {
			shinyNumber = r.nextInt(denominator + 1);
			
			// Check if we have a shiny
			if (shinyNumber <= numerator) {
				numShinies++;
			}
			
		}
		
		return numShinies;
	}

	
	public static boolean checkIfInt(String input) {
		
		try {
			int test = Integer.parseInt(input);
		}
		
		catch (Exception e) {
			return false;
		}
		return true;
		
	}
	/* This function is only called if the user gives invalid input.
	   It's function is to randomly swear at the user and then terminate the program. */
	public static void terminate (int randomNum) {
		
		// Store random swear phrases here
		String[] swearPhrases = {
			"This fucking idiot can't even give valid input.",
			"Who the hell taught you how to type?",
			"Learn to follow directions, dumbass.",
			"Stop trying to break the program you brat.",
			"What the bloody hell is wrong with you? Can you not read or something?",
			"Even a dog could follow simple directions such as these. \n Obviously you're not even fit to be a dog.",
			"Your lack of intelligence disturbs me.",
			"You thought this program would hold your hand and would let you give invalid input, think again dumbass.",
			"It is not the programmer's job to make the program idiot proof, \n it is assumed if one can use the command line to begin with that you have at least some intelligence."
		};
		System.out.println(swearPhrases[randomNum]);
		
		System.exit(0);
	}
	
	
}