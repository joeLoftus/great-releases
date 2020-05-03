package pojos;

import java.util.List;

public class SearchResult {
	private List<Show> results;
	private String page;
	private String total_results;
	private Dates dates;
	private String total_pages;
	public SearchResult(List<Show> results, String page, String total_results, Dates dates, String total_pages) {
		super();
		this.results = results;
		this.page = page;
		this.total_results = total_results;
		this.dates = dates;
		this.total_pages = total_pages;
	}
	public SearchResult() {
		super();
		
	}
	public List<Show> getResults() {
		return results;
	}
	public void setResults(List<Show> results) {
		this.results = results;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getTotal_results() {
		return total_results;
	}
	public void setTotal_results(String total_results) {
		this.total_results = total_results;
	}
	public Dates getDates() {
		return dates;
	}
	public void setDates(Dates dates) {
		this.dates = dates;
	}
	public String getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(String total_pages) {
		this.total_pages = total_pages;
	}
 
}
