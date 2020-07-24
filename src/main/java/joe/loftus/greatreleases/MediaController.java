package joe.loftus.greatreleases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import joe.loftus.data.DataController;
import joe.loftus.pojos.Show;

@RestController
public class MediaController {
	@Autowired
	private static final Logger logger = LogManager.getLogger(MediaController.class);
	private DataController dataController = new DataController();

	@RequestMapping("/")
	List<Show> getHighlyRatedMovies() throws IOException, SQLException {
		try {
			dataController.putShowsInDatabase();
			List<Show> topThree = dataController.getTopThree();
			logger.info("Success getHighlyRatedMovies");
			return topThree;
		} catch (Exception e) {
			logger.error("Error in getHighlyRatedMovies", e);
			return null;
		}
	}
}

//make a call to the endpoint several times a day.
//store data in sqllite database
//outgoing endpoints send data from sqllite database 

//today: one endpoint stores things in datbase and then returns info from that database
