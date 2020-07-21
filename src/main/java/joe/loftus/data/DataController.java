package joe.loftus.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import joe.loftus.pojos.Show;

public class DataController {

	public void setData(List<Show> shows) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/joe/loftus/greatreleases/testjava.db");
		Statement statement = conn.createStatement();
		statement.execute("DROP TABLE shows");
		statement.execute("CREATE TABLE IF NOT EXISTS shows "
				+ " (popularity TEXT, vote_count TEXT, video TEXT, poster_path TEXT, id TEXT, adult TEXT, backdrop_path TEXT, original_language TEXT, original_title TEXT, genre_ids TEXT[], title TEXT, vote_average TEXT, overview TEXT, release_date TEXT)");
		
		for (int i = 0; i < shows.size(); i++) {
			String string = "INSERT INTO shows (popularity, vote_count, video, poster_path, id, adult, backdrop_path, original_language, original_title, genre_ids, title, vote_average, overview, release_date) "
					+ "VALUES('" + String.valueOf(shows.get(i).getPopularity()).replaceAll("'","") + "','"+ String.valueOf(shows.get(i).getVote_count()).replaceAll("'","") + "','"
					+ String.valueOf(shows.get(i).getVideo()).replaceAll("'","") + "','"+ String.valueOf(shows.get(i).getPoster_path()).replaceAll("'","") + "','"
					+ String.valueOf(shows.get(i).getId()).replaceAll("'","") + "','"+ String.valueOf(shows.get(i).getAdult()).replaceAll("'","") + "','"
					+ String.valueOf(shows.get(i).getBackdrop_path()).replaceAll("'","") + "','"+ String.valueOf(shows.get(i).getOriginal_language()).replaceAll("'","") + "','"
					+ String.valueOf(shows.get(i).getOriginal_title()).replaceAll("'","") + "','"+ String.valueOf(shows.get(i).getGenre_ids()) + "','"
					+ String.valueOf(shows.get(i).getTitle()).replaceAll("'","") + "','"+ String.valueOf(shows.get(i).getVote_average()).replaceAll("'","") + "','"
					+ String.valueOf(shows.get(i).getOverview()).replaceAll("'","") + "','"+ String.valueOf(shows.get(i).getRelease_date()).replaceAll("'","") + "')";
			
			System.out.println(string);
			
			statement.execute(string);
		}

		statement.close();
		conn.close();
	}

	public ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<String>();
		try {
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:src/main/java/joe/loftus/greatreleases/testjava.db");
			Statement statement = conn.createStatement();

			statement.execute("CREATE TABLE IF NOT EXISTS shows "
					+ " (popularity TEXT, vote_count TEXT, video TEXT, poster_path TEXT, id TEXT, adult TEXT, backdrop_path TEXT, original_language TEXT, original_title TEXT, genre_ids TEXT[], title TEXT, vote_average TEXT, overview TEXT, release_date TEXT)");
			statement.execute("SELECT * FROM shows");
			ResultSet results = statement.getResultSet();
			while (results.next()) {
				data.add(results.getString("title") + " " + results.getInt("id"));
			}
			results.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
		return data;
	}

}
