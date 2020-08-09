package joe.loftus.pojos;

import java.util.Comparator;

public class ShowComparator implements Comparator<Show>{
	@Override
	public int compare(Show show1, Show show2) {
		return Float.valueOf(show2.getVote_average()).compareTo(Float.valueOf(show1.getVote_average()));
	}
}