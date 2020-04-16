package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Show {
	private String title;
	private String year;
	private String imdbID;
	private String type;
	private String poster;
	
	public Show(String title, String year, String imdbID, String type, String poster) {
		super();
		this.title = title;
		this.year = year;
		this.imdbID = imdbID;
		this.type = type;
		this.poster = poster;
	}

	public Show() {
		super();
	}

	@JsonProperty("Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@JsonProperty("Year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@JsonProperty("imdbID")
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	@JsonProperty("Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("Poster")
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	
}
