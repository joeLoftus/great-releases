package pojos;

public class Dates {
	private String maximum;
	private String minimum;
	public Dates(String maximum, String minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}
	public Dates() {
		super();
		
	}
	public String getMaximum() {
		return maximum;
	}
	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}
	public String getMinimum() {
		return minimum;
	}
	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}
	
}
