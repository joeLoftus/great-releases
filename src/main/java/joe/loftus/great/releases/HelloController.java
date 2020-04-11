package joe.loftus.great.releases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    @Autowired
    private Environment env;    
    
    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }
    
    @RequestMapping("/movies")
    String movies() throws IOException {
    	String apiKey = env.getProperty("apikey");
    	URL url = new URL("http://www.omdbapi.com/?apikey=" + apiKey + "&t=Lord");
    	HttpURLConnection con = (HttpURLConnection) url.openConnection();
    	con.setRequestMethod("GET");
    	return FullResponseBuilder.getFullResponse(con);
    }
}
