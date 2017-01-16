package Objects;

public class Info {
	String meno, priezvisko, id;

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getPriezvisko() {
		return priezvisko;
	}

	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Info(String id, String meno, String priezvisko) {
		super();
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.id = id;
	}
}
