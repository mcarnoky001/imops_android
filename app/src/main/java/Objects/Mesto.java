package Objects;

public class Mesto {
	String id, mesto;

	public Mesto(String id, String mesto) {
		super();
		this.id = id;
		this.mesto = mesto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
}
