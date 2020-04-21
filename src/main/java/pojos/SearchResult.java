package pojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
	private List<SearchResultShow> Search;
	private String totalResults;
	private String Response;

	public SearchResult(List<SearchResultShow> Search, String totalResults, String Response) {
		super();
		this.Search = Search;
		this.totalResults = totalResults;
		this.Response = Response;
	}

	public SearchResult() {
		super();
	}

	@JsonProperty("Search")
	public List<SearchResultShow> getSearch() {
		return Search;
	}

	public void setSearch(List<SearchResultShow> Search) {
		this.Search = Search;
	}

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	@JsonProperty("Response")
	public String getResponse() {
		return Response;
	}

	public void setResponse(String Response) {
		this.Response = Response;
	}

}
