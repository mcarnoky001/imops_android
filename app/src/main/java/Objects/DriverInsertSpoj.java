package Objects;

public class DriverInsertSpoj {
	String spoj, poradie, mesto, start, kilometre;

	public String getSpoj() {
		return spoj;
	}

	public void setSpoj(String spoj) {
		this.spoj = spoj;
	}

	public DriverInsertSpoj(String spoj, String poradie, String mesto,
			String start, String kilometre) {
		super();
		this.spoj = spoj;
		this.poradie = poradie;
		this.mesto = mesto;
		this.start = start;
		this.kilometre = kilometre;
	}

	public String getPoradie() {
		return poradie;
	}

	public void setPoradie(String poradie) {
		this.poradie = poradie;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getKilometre() {
		return kilometre;
	}

	public void setKilometre(String kilometre) {
		this.kilometre = kilometre;
	}
}
