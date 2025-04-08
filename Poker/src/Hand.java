import java.util.Arrays;

public class Hand {
	private Card[] cards;
	private int maxRank;
	private static int HAND_SIZE = 5;
	private String type;

	public Hand(Card[] cards) {
		this.cards = new Card[HAND_SIZE];
		for (int i = 0; i < HAND_SIZE; i++) {
			Card c = cards[i];
			int rank = c.getRank();
			if (rank > maxRank)
				maxRank = rank;
			this.cards[i] = c;
		}
	}

	public Card[] sortByRank(Card[] arr) {
		Card[] sorted = new Card[arr.length];
		for (int i = 0; i < arr.length; i++)
			sorted[i] = arr[i];
		Arrays.sort(sorted);
		return sorted;
	}

	public String getType() {
		Card[] sorted = sortByRank(cards);
		boolean st = true, fl = true;
		int[] counts = new int[13];

		int count2 = 0, rank1 = sorted[0].getRank(), 
				rank2 = 0, count1 = ++counts[rank1 - 2];

		for (int i = 1; i < sorted.length; i++) {
			Card c1 = sorted[i];
			Card c2 = sorted[i - 1];

			fl = fl && c1.getSuit() == c2.getSuit();

			int r1 = c1.getRank();
			int r2 = c2.getRank();
			boolean ace = r1 == 14 && r2 == 5;
			boolean inc = r1 - 1 == r2 || ace;
			st = st && inc;

			int c = ++counts[r1 - 2];
			if (c > count1 || 
					(c == count1 && r1 > rank1)) {
				count1 = c;
				rank1 = r1;
			}
		}
		if (st && fl)
			return "straight flush";
		if (count1 == 4)
			return "four of a kind";
		for (int i = 0; i < counts.length; i++) {
			int r = i + 2;
			if (counts[i] > 1 && r != rank1) {
				count2 = counts[i];
				rank2 = r;
			}
		}
		if (count1 == 3 && count2 == 2)
			return "" + rank1 + " full of " + rank2;
		if (fl)
			return "flush";
		if (st)
			return "straight";
		if (count1 == 3)
			return "set of " + rank1;
		if (count1 == 2 && count2 == 2)
			return "two pair " + rank1 + " and " + rank2;
		if (count1 == 2)
			return "pair of " + rank1;
		return "high card " + rank1;
	}

	public String toString() {
		String s = "";
		for (Card c : cards)
			s += c + " ";
		return s;
	}

	public static void main(String[] args) {

		Card[] arr = { new Card(3, 's'), new Card(4, 'd'), new Card(5, 'd'), new Card(6, 'd'), new Card(14, 'd') };
		Hand h = new Hand(arr);
		System.out.println(h);
		System.out.println(h.getType());

	}
}
