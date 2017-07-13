package application;

public class TransformationInfo {
	private String name;
	private String line1;
	private String line2;
	private String original;
	private String results;
	
	public TransformationInfo(String name, String line1, String line2, String original, String results) {
		super();
		this.name = name;
		this.line1 = line1;
		this.line2 = line2;
		this.original = original;
		this.results = results;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}
	
	
}
