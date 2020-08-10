package joe.loftus.greatreleases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import joe.loftus.data.DataController;

@Component
public class ScheduledUpdater {
	private static final Logger log = LoggerFactory.getLogger(ScheduledUpdater.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
	private DataController dataController = new DataController();

	@Scheduled(fixedRate = 3600000)
	public void reportCurrentTime() {
		log.info("Updating Database {}", dateFormat.format(new Date()));
		try {
			dataController.putShowsInDatabase();
			log.info("Success updating database");
		} catch (Exception e) {
			log.error("Error updating database", e);
		}
	}
}
