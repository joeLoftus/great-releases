package joe.loftus.pojos;

import java.util.Comparator;

public class ShowComparator implements Comparator<Show>{
	public int compare(Show show1, Show show2) {
		if (Float.parseFloat(show1.getVote_average()) > Float.parseFloat(show2.getVote_average())) {
			return 1;
		}
		return 0;
	}
}