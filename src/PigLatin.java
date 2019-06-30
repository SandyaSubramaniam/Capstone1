import java.util.Scanner;
import java.util.StringTokenizer;

public class PigLatin {

	public static void main(String[] args) {

		System.out.println("Welcome to the Pig Latin Translator!");

		Scanner scnr = new Scanner(System.in);

		char userchoice = 'y';

		do {

			System.out.println("Enter a line to be translated:");

			String userword = scnr.nextLine();

			if (!userword.equals("")) {

				userword = userword.trim();

				StringTokenizer t = new StringTokenizer(userword);

				String phrase = "";

				while (t.hasMoreTokens()) {

					String word = t.nextToken();

					phrase = phrase + translateToPigLatin(word) + " ";

				}
				System.out.println(phrase);

				System.out.println("Translate another line? (y/n):");

				userchoice = scnr.nextLine().charAt(0);
			}

		} while ((userchoice == 'Y') || (userchoice == 'y'));

		System.out.println("Goodbye!");

		scnr.close();
	}

	public static boolean isValidateWord(String userWord) {

		return userWord.matches("[a-zA-Z]+") || userWord.matches(".*\\p{Punct}");

	}

	public static String getPunctuation(String userWord) {

		String result = "";

		String lastChar = Character.toString(userWord.charAt(userWord.length() - 1));

		if (lastChar.matches(".*\\p{Punct}")) {

			result = lastChar;

		}

		return result;
	}

	public static String extractPuncuationFromWord(String userWord, String lastCharPunct) {

		String result = userWord;

		if (lastCharPunct.length() > 0) {

			result = userWord.substring(0, userWord.length() - 1);

		}

		return result;
	}

	public static String translateWordWithFirstCharacterConsonent(String userword) {

		String result = userword;

		String front = "";

		String back = "";

		for (int i = 0; i < userword.length(); i++) {

			if (isCharVowel(userword, i)) {

				front = userword.substring(0, i);

				back = userword.substring(i);

				result = back + front;

				break;
			}
		}
		return result + "ay";
	}

	public static boolean isCharVowel(String userword, int position) {

		String charAtPosition = Character.toString(userword.charAt(position));

		return charAtPosition.matches("(?i)[aeiou]");
	}

	public static String translateToPigLatin(String userWord) {

		String result = "";

		if (isValidateWord(userWord)) {

			String lastCharPunct = getPunctuation(userWord);

			String userWordWithoutPunct = extractPuncuationFromWord(userWord, lastCharPunct);

			String piglatin = "";

			if (isCharVowel(userWordWithoutPunct, 0)) {

				piglatin = userWordWithoutPunct + "way";

			} else {

				piglatin = translateWordWithFirstCharacterConsonent(userWordWithoutPunct);

			}

			result = piglatin + lastCharPunct;

		} else {

			result = userWord;
		}

		return result;
	}

}
