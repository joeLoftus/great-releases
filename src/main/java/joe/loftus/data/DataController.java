package joe.loftus.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import joe.loftus.pojos.SearchResult;
import joe.loftus.pojos.Show;
import joe.loftus.pojos.ShowComparator;

@Configuration
@PropertySource("classpath:application.properties")
public class DataController {
	private double ratingThreshold = 8.5;
	private List<Show> topThree;
	private ClassLoader loader = Thread.currentThread().getContextClassLoader();
	private Properties properties = new Properties();
	private ObjectMapper mapper = new ObjectMapper();
	private String apiKey;
	
	public DataController(){
		super();
		
		try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
		    properties.load(resourceStream);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		this.apiKey = properties.getProperty("apikey");
	}

	public List<Show> getTopThree() {
		return topThree;
	}

	public void setTopThree(List<Show> topThree) {
		this.topThree = topThree;
	}

	List<Show> returnThreeMovies(List<Show> originalList) {
		Collections.sort(originalList, new ShowComparator());
		return originalList.subList(0, 3);
	}

	public void putShowsInDatabase() throws SQLException {
		List<Show> highlyRated = new ArrayList<Show>();
		try {
			URL url = new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey);
			SearchResult initialResult = this.mapper.readValue(url, SearchResult.class);
			List<Show> initialShows = initialResult.getResults();
			int paginationIndex = Integer.parseInt(initialResult.getTotal_pages());

			// Loop through all shows on first initialResult to prevent call the first api
			// call again
			for (Show show : initialShows) {
				if (Double.valueOf(show.getVote_average()) >= ratingThreshold) {
					highlyRated.add(show);
				}
			}

			// make an api call to each page except the first and put all qualified shows in
			// the final result
			while (paginationIndex > 1) {
				URL pageUrl = new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=" + this.apiKey + "&page="
						+ paginationIndex);
				SearchResult pageResult = mapper.readValue(pageUrl, SearchResult.class);
				List<Show> pageShows = pageResult.getResults();
				for (Show pageShow : pageShows) {
					if (Double.valueOf(pageShow.getVote_average()) >= ratingThreshold) {
						highlyRated.add(pageShow);
					}
				}
				paginationIndex = paginationIndex - 1;
			}

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setData(highlyRated);
		this.setTopThree(highlyRated.subList(0, 3));
	}

	public void setData(List<Show> shows) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/joe/loftus/greatreleases/testjava.db");
		Statement statement = conn.createStatement();
		statement.execute("DROP TABLE IF EXISTS shows");
		statement.execute("CREATE TABLE IF NOT EXISTS shows "
				+ " (popularity TEXT, vote_count TEXT, video TEXT, poster_path TEXT, id TEXT, adult TEXT, backdrop_path TEXT, original_language TEXT, original_title TEXT, genre_ids TEXT[], title TEXT, vote_average TEXT, overview TEXT, release_date TEXT, UNIQUE(id, title));");

		for (int i = 0; i < shows.size(); i++) {
			String string = "INSERT OR IGNORE INTO shows (popularity, vote_count, video, poster_path, id, adult, backdrop_path, original_language, original_title, genre_ids, title, vote_average, overview, release_date) "
					+ "VALUES('" + String.valueOf(shows.get(i).getPopularity()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getVote_count()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getVideo()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getPoster_path()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getId()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getAdult()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getBackdrop_path()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getOriginal_language()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getOriginal_title()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getGenre_ids()) + "','"
					+ String.valueOf(shows.get(i).getTitle()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getVote_average()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getOverview()).replaceAll("'", "") + "','"
					+ String.valueOf(shows.get(i).getRelease_date()).replaceAll("'", "") + "')";
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
