package Objects;

public class Ceny {
	String meno;
	float hodnota;

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public float getHodnota() {
		return hodnota;
	}

	public void setHodnota(float hodnota) {
		this.hodnota = hodnota;
	}

	public Ceny(String meno, float hodnota) {
		super();
		this.meno = meno;
		this.hodnota = hodnota;
	}
}
