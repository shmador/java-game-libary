import java.util.HashSet;
public class HangMan {
	private String[] man;
	private char[] word;
	private char[] hiddenWord;
	private int guesses;
	private HashSet<Character> prevGuesses = new HashSet<>();
	private final int MAX_GUESSES = 5;
	public HangMan(String word) {
		man = new String[] {
			"  \n"
		+ 	"   \n"
		+ 	"   ",
			" o\n"
		+	"   \n"
		+	"   ",
			" o\n"
		+	"/  \n"
		+	"   ",
			" o\n"
		+	"/| \n"
		+	"   ",
			" o\n"
		+ 	"/|\\\n"
		+ 	"   ",
			" o\n"
		+ 	"/|\\\n"
		+ 	"/  ",
			" o\n"
		+	"/|\\\n"
		+	"/ \\"
		};
		this.word = word.toCharArray();
		int len = this.word.length;
		hiddenWord = new char[len];
		for (int i = 0; i < len; i++) {
			char c = this.word[i];
			if (c == ' ')
				hiddenWord[i] = c;
			else
				hiddenWord[i] = '-';
		}
	}
	public HangMan() {
		this(generateRandomWord());
	}
	public static String generateRandomWord() {
		String[] randomWords = {
				"HANGMAN", "CAMERA", "SMOOTH", "BLENDER", "JAVA", "PUZZLE", "EULER", "SUDOKU", 
				"ASSET", "ROUTER", "LINUX", "GIT", "SCRIPT", "MATRIX", "CLIENT", "NETWORK", 
				"CIPHER", "SERVER", "PIXEL", "SPRITE", "COMET", "GALAXY", "TIGER", "JUNGLE", 
				"OCEAN", "MOUNTAIN", "ISLAND", "DESERT", "PYTHON", "COFFEE", "WIZARD", 
				"DRAGON", "PYRAMID", "EMERALD", "ZEBRA", "VIOLET", "SHADOW", "MYSTERY", 
				"PHOENIX"
				};
		int rand = (int)(Math.random() * (randomWords.length));
		return randomWords[rand];
	}
	public boolean guess(char c) { //returns if guess is valid
		if ('Z' < c || c < 'A') {
			System.out.println("INVALID");
			return false;
		}
		if (!prevGuesses.add(c)) {
			System.out.println("ALREADY GUESSED");
			return false;
		}
		boolean correct = false;
		for (int i = 0; i < word.length; i ++) {
			if (c == word[i]) {
				hiddenWord[i] = c;
				correct = true;
			}
		}
		if (!correct) {
			guesses++;
		}
		return true;
	}
	
	public boolean win() {
		return new String(word).equals(new String(hiddenWord));
	}
	
	public boolean lose() {
		return guesses > MAX_GUESSES;
	}
	public String toString() {
		String str = "";
		if (lose())
			str = new String(word);
		else
			str = new String(hiddenWord);
		return man[guesses] + "\n" + str;
	}
}
