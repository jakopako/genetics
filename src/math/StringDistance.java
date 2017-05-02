package math;

/**
 * This class for now just implements one method of calculating a distance
 * between two strings.
 * 
 * @author Jakob Dhondt
 *
 */
public class StringDistance {

	/**
	 * This method calculates the levenshtein distance between two given strings.
	 * 
	 * @param one The first string.
	 * @param two The second string.
	 * @return The levenshtein distance between the two given strings.
	 */
	public static int levenshtein(String one, String two){
		int[][] dists = new int[one.length()+1][two.length()+1];
		for (int i = 1; i<=one.length(); i++){
			dists[i][0] = i;
		}
		for (int j = 1; j<=two.length(); j++){
			dists[0][j] = j;
		}
		for (int j = 0; j<two.length(); j++){
			for (int i = 0; i<one.length(); i++){
				int s_cost = 1;
				if (one.charAt(i) == two.charAt(j)){
					s_cost = 0;
				}
				dists[i+1][j+1] = Math.min(Math.min(dists[i][j+1]+1,
						dists[i+1][j] + 1),
						dists[i][j] + s_cost);
			}
		}
		return dists[one.length()][two.length()];
	}
}
