package pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Show {
	private String Title;
	private String Year;
	private String Rated;
	private String Released;
	private String Runtime;
	private String Genre;
	private String Director;
	private String Writer;
	private String Actors;
	private String Plot;
	private String Language;
	private String Country;
	private String Awards;
	private String Poster;
	private List<ShowRating> Ratings;
	private String Metascore;
	private String imdbRating;
	private String imdbVotes;
	private String imdbId;
	private String Type;
	private String totalSeasons;
	private String Reponse;

	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Show(String title, String year, String rated, String released, String runtime, String genre, String director,
			String writer, String actors, String plot, String language, String country, String awards, String poster,
			List<ShowRating> ratings, String metascore, String imdbRating, String imdbVotes, String imdbId, String type,
			String totalSeasons, String reponse) {
		super();
		Title = title;
		Year = year;
		Rated = rated;
		Released = released;
		Runtime = runtime;
		Genre = genre;
		Director = director;
		Writer = writer;
		Actors = actors;
		Plot = plot;
		Language = language;
		Country = country;
		Awards = awards;
		Poster = poster;
		Ratings = ratings;
		Metascore = metascore;
		this.imdbRating = imdbRating;
		this.imdbVotes = imdbVotes;
		this.imdbId = imdbId;
		Type = type;
		this.totalSeasons = totalSeasons;
		Reponse = reponse;
	}

	@JsonProperty("Country")
	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	@JsonProperty("Title")
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@JsonProperty("Year")
	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	@JsonProperty("Rated")
	public String getRated() {
		return Rated;
	}

	public void setRated(String rated) {
		Rated = rated;
	}

	@JsonProperty("Released")
	public String getReleased() {
		return Released;
	}

	public void setReleased(String released) {
		Released = released;
	}

	@JsonProperty("Runtime")
	public String getRuntime() {
		return Runtime;
	}

	public void setRuntime(String runtime) {
		Runtime = runtime;
	}

	@JsonProperty("Genre")
	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	@JsonProperty("Director")
	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	@JsonProperty("Writer")
	public String getWriter() {
		return Writer;
	}

	public void setWriter(String writer) {
		Writer = writer;
	}

	@JsonProperty("Actors")
	public String getActors() {
		return Actors;
	}

	public void setActors(String actors) {
		Actors = actors;
	}

	@JsonProperty("Plot")
	public String getPlot() {
		return Plot;
	}

	public void setPlot(String plot) {
		Plot = plot;
	}

	@JsonProperty("Language")
	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	@JsonProperty("Awards")
	public String getAwards() {
		return Awards;
	}

	public void setAwards(String awards) {
		Awards = awards;
	}

	@JsonProperty("Poster")
	public String getPoster() {
		return Poster;
	}

	public void setPoster(String poster) {
		Poster = poster;
	}

	@JsonProperty("Ratings")
	public List<ShowRating> getRatings() {
		return Ratings;
	}

	public void setRatings(List<ShowRating> ratings) {
		Ratings = ratings;
	}

	@JsonProperty("Metascore")
	public String getMetascore() {
		return Metascore;
	}

	public void setMetascore(String metascore) {
		Metascore = metascore;
	}

	@JsonProperty("imdbRating")
	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	@JsonProperty("imdbVotes")
	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	@JsonProperty("imdbID")
	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	@JsonProperty("Type")
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@JsonProperty("totalSeasons")
	public String getTotalSeasons() {
		return totalSeasons;
	}

	public void setTotalSeasons(String totalSeasons) {
		this.totalSeasons = totalSeasons;
	}

	@JsonProperty("Response")
	public String getReponse() {
		return Reponse;
	}

	public void setReponse(String reponse) {
		Reponse = reponse;
	}

}
