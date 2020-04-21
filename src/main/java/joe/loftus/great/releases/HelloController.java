package joe.loftus.great.releases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pojos.SearchResult;
import pojos.SearchResultShow;
import pojos.Show;

@RestController
public class HelloController {
	@Autowired
	private Environment env;

	@RequestMapping("/")
	SearchResult movies() throws IOException {
		String apiKey = env.getProperty("apikey");
		URL url = new URL("http://www.omdbapi.com/?apikey=" + apiKey + "&s=Game");
		ObjectMapper mapper = new ObjectMapper();

		try {
			SearchResult list = mapper.readValue(url, SearchResult.class);
			return list;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/show")
	Show showById() throws IOException {
		String apiKey = env.getProperty("apikey");
		URL url = new URL("http://www.omdbapi.com/?apikey=" + apiKey + "&s=Game");
		ObjectMapper mapper = new ObjectMapper();

		try {
			SearchResult list = mapper.readValue(url, SearchResult.class);
			URL url2 = new URL("http://www.omdbapi.com/?apikey=" + apiKey + "&i=" + list.getSearch().get(0).getImdbID());
 			return mapper.readValue(url2, Show.class);
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
