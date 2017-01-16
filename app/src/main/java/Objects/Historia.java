package Objects;

public class Historia {
	String id, pouz, jazda, typ_listka, miesto, z, ciel, cas, cena, nastupil,
			benefit, text;

	public Historia(String id, String pouz, String jazda, String typ_listka,
			String miesto, String z, String ciel, String cas, String cena,
			String nastupil, String benefit, String text) {
		super();
		this.id = id;
		this.pouz = pouz;
		this.jazda = jazda;
		this.typ_listka = typ_listka;
		this.miesto = miesto;
		this.z = z;
		this.ciel = ciel;
		this.cas = cas;
		this.cena = cena;
		this.nastupil = nastupil;
		this.benefit = benefit;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPouz() {
		return pouz;
	}

	public void setPouz(String pouz) {
		this.pouz = pouz;
	}

	public String getJazda() {
		return jazda;
	}

	public void setJazda(String jazda) {
		this.jazda = jazda;
	}

	public String getTyp_listka() {
		return typ_listka;
	}

	public void setTyp_listka(String typ_listka) {
		this.typ_listka = typ_listka;
	}

	public String getMiesto() {
		return miesto;
	}

	public void setMiesto(String miesto) {
		this.miesto = miesto;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
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

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public String getNastupil() {
		return nastupil;
	}

	public void setNastupil(String nastupil) {
		this.nastupil = nastupil;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

}
