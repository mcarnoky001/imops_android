package Objects;

public class Rides {
	String id, trasa, start, ciel, cas, pocet_miest, text;

	public Rides(String id, String trasa, String start, String ciel,
			String cas, String pocet_miest, String text) {
		super();
		this.id = id;
		this.trasa = trasa;
		this.start = start;
		this.ciel = ciel;
		this.cas = cas;
		this.pocet_miest = pocet_miest;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrasa() {
		return trasa;
	}

	public void setTrasa(String trasa) {
		this.trasa = trasa;
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

	public String getCas() {
		return cas;
	}

	public void setCas(String cas) {
		this.cas = cas;
	}

	public String getPocet_miest() {
		return pocet_miest;
	}

	public void setPocet_miest(String pocet_miest) {
		this.pocet_miest = pocet_miest;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
