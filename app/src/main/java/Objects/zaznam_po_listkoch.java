package Objects;

public class zaznam_po_listkoch {
	Integer ztp, student, dochod, normal, index;
	Float cena;

	public zaznam_po_listkoch(Integer ztp, Integer student, Integer dochod,
			Integer normal, Integer index, Float cena) {
		super();
		this.ztp = ztp;
		this.student = student;
		this.dochod = dochod;
		this.normal = normal;
		this.index = index;
		this.cena = cena;
	}

	public Integer getZtp() {
		return ztp;
	}

	public void setZtp(Integer ztp) {
		this.ztp = ztp;
	}

	public Integer getStudent() {
		return student;
	}

	public void setStudent(Integer student) {
		this.student = student;
	}

	public Integer getDochod() {
		return dochod;
	}

	public void setDochod(Integer dochod) {
		this.dochod = dochod;
	}

	public Integer getNormal() {
		return normal;
	}

	public void setNormal(Integer normal) {
		this.normal = normal;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}

}
