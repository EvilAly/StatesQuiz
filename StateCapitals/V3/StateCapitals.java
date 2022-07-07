package V3;

import java.io.*;
import java.util.*;

/**
 * Asks user total number of states to guess at beginning instead of asking
 * after each guess. Allows to play multiple times. Incorrect responses return
 * red text.
 **/
public class StateCapitals {

	public static void main(String[] args) {
		// create map
		Scanner s = new Scanner(System.in);
		Map<String, String> stateCaps = new HashMap<String, String>();
		// load state info
		Caps.loadStates(stateCaps);
		// start game loop
		System.out.println("Hello. Welcome to the State Capitals Quiz!");

		boolean play = true;

		while (play) {
			System.out.println("How many states would you like to test for?");
			int states = s.nextInt();

			playGame(stateCaps, states);

			// play again?

			boolean valid = false;
			while (!valid) {
				s.nextLine();
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
		System.out.println("Thank you for playing!");
		System.exit(0);
	}

	public static void playGame(Map<String, String> sc, int p) {
		Scanner s = new Scanner(System.in);
		int left = p;
		int correct = 0;
		while (left > 0) {
			left--;

			String state = randomState(sc);

			// list state
			// ask user for capital
			System.out.println("\nWhat is the state capital of " + state + "?");
			String guess = s.nextLine();

			// is user correct?
			correct = checkCapital(sc, state, guess, correct);
			// if so count it
		}

		// stop play and close game
		System.out.println("\nYou played " + p + " games and answered " + correct + " state capitals correctly.");
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
					System.err.println("I'm sorry, but the capital of " + key + " is " + sc.get(key));
				}

			}

		}
		return c;

	}
}
