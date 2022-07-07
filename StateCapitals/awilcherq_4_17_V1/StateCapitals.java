package awilcherq_4_17_V1;

import java.io.*;
import java.util.*;

public class StateCapitals {

	public static void main(String[] args) {
		// create map
		Scanner s = new Scanner(System.in);
		Map<String, String> stateCaps = new HashMap<String, String>();
		// load state info
		loadStates(stateCaps);
		// start game loop
		boolean play = true;
		int total = 0;
		int correct = 0;
		while (play) {
			total++;

			String state = randomState(stateCaps);

			// list state
			// ask user for capital
			System.out.println("\nWhat is the state capital of " + state + "?");
			String guess = s.nextLine();

			// is user correct?
			correct = checkCapital(stateCaps, state, guess, correct);
			// if so count it

			// play again?
			boolean valid = false;
			while (!valid) {
				System.out.println("\nWould you like to play again? yes or no?");
				String input = s.nextLine().toLowerCase();

				if (input.equals("no")) {
					play = false;
					valid = true;
				} else if (!input.equals("yes")) {
					System.out.println("That is not a valid response.");
				} else
					valid = true;
			}
		}
		// stop play and close game
		System.out.println("\nYou played " + total + " games and answered " + correct + " state capitals correctly.");
		System.out.println("Thank you for playing!");
		System.exit(0);

	}

	public static void loadStates(Map<String, String> sc) {
		Scanner s = new Scanner(System.in);
		String filename = FileLoc.pickLocRead();
		// Open the file.
		File file = new File(filename);
		Scanner inputFile = null;
		try {
			inputFile = new Scanner(file);

			// Read lines from the file until no more are left.
			while (inputFile.hasNext()) {
				// Read in the file.
				String line = inputFile.nextLine();
				StringTokenizer st = new StringTokenizer(line, "-");
				// Read a record from the file.
				String state = st.nextToken().trim();
				String capital = st.nextToken().trim();

				sc.put(state, capital);

			}

		}

		// catch exception
		catch (

		IOException ioe) {
			System.err.println("IO Exception.  Need to exit");
			System.exit(0);

		} finally {
			// Close the file.
			inputFile.close();

		}
	}

	public static String randomState(Map<String, String> sc) {
		// create set of states
		Set<String> set = (sc.keySet());
		String[] keys = set.toArray(new String[set.size()]);
		// pick a random number
		int number = (int) (Math.random() * set.size());
		// get and return that state
		String state = keys[number];

		return state;

	}

	public static int checkCapital(Map<String, String> sc, String state, String guess, int c) {
		Set<String> keys = sc.keySet();
		for (String key : keys) {
			if (key.equals(state)) {
				String cap = sc.get(key);
				if (cap.equals(guess)) {
					c++;
					System.out.println("That is correct!");
				} else {
					System.out.println("I'm sorry, but the capital of " + key + " is " + sc.get(key));
				}

			}

		}
		return c;

	}
}
