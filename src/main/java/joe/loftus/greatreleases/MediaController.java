package joe.loftus.greatreleases;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import joe.loftus.pojos.SearchResult;
import joe.loftus.pojos.Show;

@RestController
public class MediaController {
	@Autowired
	private Environment env;
	private double ratingThreshold = 8.5;
	private static final Logger logger = LogManager.getLogger(MediaController.class);

	@RequestMapping("/")
	List<Show> getHighlyRatedMovies() throws IOException {
		List<Show> highlyRated = new ArrayList<Show>();
		String apiKey = env.getProperty("apikey");
		URL url = new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey);
		ObjectMapper mapper = new ObjectMapper();

		// TODO: have data access class handle this try catch
		try {
			SearchResult initialResult = mapper.readValue(url, SearchResult.class);
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
				URL pageUrl = new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=" + apiKey + "&page="
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
			try {
				logger.info("Success getHighlyRatedMovies");
				//store highlyRated in sqllite database
				//return .get  from sql lite database
				return highlyRated;
			} catch (Exception e) {
				logger.error("Error in getHighlyRatedMovies", e);
				return null;
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

//make a call to the endpoint several times a day.
//store data in sqllite database
//outgoing endpoints send data from sqllite database 

//today: one endpoint stores things in datbase and then returns info from that database
