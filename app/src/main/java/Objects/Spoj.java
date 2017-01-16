package Objects;

public class Spoj {
	int id, kilometre, pocet;
	String odchod, prichod, trvanie, text, start, ciel;

	public Spoj(String id, String kilometre, String odchod, String prichod,
			String trvanie, String text, String pocet, String start, String ciel) {
		super();
		this.id = Integer.parseInt(id);
		this.kilometre = Integer.parseInt(kilometre);
		this.pocet = Integer.parseInt(pocet);
		this.odchod = odchod;
		this.prichod = prichod;
		this.trvanie = trvanie;
		this.text = text;
		this.start = start;
		this.ciel = ciel;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getCiel() {
		return ciel;
	}

	public void setCiel(String ciel) {
		this.ciel = ciel;
	}

	public int getPocet() {
		return pocet;
	}

	public void setPocet(int pocet) {
		this.pocet = pocet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKilometre() {
		return kilometre;
	}

	public void setKilometre(int kilometre) {
		this.kilometre = kilometre;
	}

	public String getOdchod() {
		return odchod;
	}

	public void setOdchod(String odchod) {
		this.odchod = odchod;
	}

	public String getPrichod() {
		return prichod;
	}

	public void setPrichod(String prichod) {
		this.prichod = prichod;
	}

	public String getTrvanie() {
		return trvanie;
	}

	public void setTrvanie(String trvanie) {
		this.trvanie = trvanie;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
