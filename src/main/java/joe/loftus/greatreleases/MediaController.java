package joe.loftus.greatreleases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import joe.loftus.data.DataController;
import joe.loftus.pojos.Show;

@RestController
public class MediaController {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(ScheduledUpdater.class);
	private DataController dataController = new DataController();
	
	@RequestMapping("/")
	List<Show> getData() throws IOException, SQLException {
		try {
			return dataController.getGraphData();
		} catch (Exception e) {
			logger.error("Error in getData", e);
			return null;
		}
	}
	
	@RequestMapping("/english")
	List<Show> getEnglishData() throws IOException, SQLException {
		try {
			return dataController.getEnglishGraphData();
		} catch (Exception e) {
			logger.error("Error in getData", e);
			return null;
		}
	}
}
