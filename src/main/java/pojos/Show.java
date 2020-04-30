package pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Show {
	@JsonProperty("popularity")
	private String popularity;
	@JsonProperty("vote_count")
	private String vote_count;
	@JsonProperty("video")
	private String video;
	@JsonProperty("poster_path")
	private String poster_path;
	@JsonProperty("id")
	private String id;
	@JsonProperty("adult")
	private String adult;
	@JsonProperty("backdrop_path")
	private String backdrop_path;
	@JsonProperty("original_language")
	private String original_language;
	@JsonProperty("original_title")
	private String original_title;
	@JsonProperty("genre_ids")
	private List<String> genre_ids;
	@JsonProperty("title")
	private String title;
	@JsonProperty("vote_average")
	private String vote_average;
	@JsonProperty("overview")
	private String overview;
	@JsonProperty("release_date")
	private String release_date;
	
	public Show() {
		// TODO Auto-generated constructor stub
	}



	public Show(String popularity, String vote_count, String video, String poster_path, String id, String adult,
			String backdrop_path, String original_language, String original_title, List<String> genre_ids, String title,
			String vote_average, String overview, String release_date) {
		super();
		this.popularity = popularity;
		this.vote_count = vote_count;
		this.video = video;
		this.poster_path = poster_path;
		this.id = id;
		this.adult = adult;
		this.backdrop_path = backdrop_path;
		this.original_language = original_language;
		this.original_title = original_title;
		this.genre_ids = genre_ids;
		this.title = title;
		this.vote_average = vote_average;
		this.overview = overview;
		this.release_date = release_date;
	}



	public List<String> getGenre_ids() {
		return genre_ids;
	}



	public void setGenre_ids(List<String> genre_ids) {
		this.genre_ids = genre_ids;
	}



	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	public String getVote_count() {
		return vote_count;
	}

	public void setVote_count(String vote_count) {
		this.vote_count = vote_count;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdult() {
		return adult;
	}

	public void setAdult(String adult) {
		this.adult = adult;
	}

	public String getBackdrop_path() {
		return backdrop_path;
	}

	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}

	public String getOriginal_language() {
		return original_language;
	}

	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVote_average() {
		return vote_average;
	}

	public void setVote_average(String vote_average) {
		this.vote_average = vote_average;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
	

}
