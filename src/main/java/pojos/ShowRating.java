package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowRating {
	private String Source;
	private String Value;

	public ShowRating() {
		super();
	}

	public ShowRating(String source, String value) {
		super();
		Source = source;
		Value = value;
	}


	@JsonProperty("Source")
	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	@JsonProperty("Value")
	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

}
