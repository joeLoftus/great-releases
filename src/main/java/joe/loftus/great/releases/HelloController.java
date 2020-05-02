package joe.loftus.great.releases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojos.SearchResult;
import pojos.Show;

@RestController
public class HelloController {
	@Autowired
	private Environment env;

	@RequestMapping("/")
	List<Show> movies() throws IOException {
		String apiKey = env.getProperty("apikey");
		URL url = new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();

		try {
			SearchResult result = mapper.readValue(url, SearchResult.class);
			List<Show> shows = result.getResults();
			List<Show> highlyRated = new ArrayList<Show>();
			
			for (Show show : shows) {

				if (Double.valueOf(show.getVote_average()) >= 8.5) {

					highlyRated.add(show);
				}
			}
			
			int pagesLeft = Integer.parseInt(result.getTotal_pages());
			while (pagesLeft > 0) {
				URL url2 = new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey + "&page=" + pagesLeft);
				SearchResult result2 = mapper.readValue(url2, SearchResult.class);
				List<Show> shows2 = result2.getResults();
				for (Show show2 : shows2) {

					if (Double.valueOf(show2.getVote_average()) >= 8.5) {

						highlyRated.add(show2);
					}
				}
				pagesLeft = pagesLeft - 1;
			}


			return highlyRated;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * first thing - return all results with over 8.5 in is results[0].vote_average
	 * 
	 * To get all result - look through all pages to look through all pages save
	 * number of pages make that many queries minus original
	 * 
	 * 
	 * in each page look at each result and its vote average if vote average is
	 * greater than 8.5 save show
	 * 
	 * soon will we pick a database that works well with our server - guessing
	 * sqlite - could practice with mongo/sql for fun
	 */
}
