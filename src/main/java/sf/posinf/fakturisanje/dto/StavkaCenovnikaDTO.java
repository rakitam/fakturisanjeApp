package sf.posinf.fakturisanje.dto;

import com.sun.istack.NotNull;

public class StavkaCenovnikaDTO {

	private long id;
	@NotNull
	private float cena;
	@NotNull
	private CenovnikDTO cenovnik;
	@NotNull
	private RobaUslugaDto robaUsluga;

	private float cenaSaPdv;

	public StavkaCenovnikaDTO() {
	}

	public StavkaCenovnikaDTO(long id, float cena, CenovnikDTO cenovnik, RobaUslugaDto robaUsluga, float cenaSaPdv) {
		this.id = id;
		this.cena = cena;
		this.cenovnik = cenovnik;
		this.robaUsluga = robaUsluga;
		this.cenaSaPdv = cenaSaPdv;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public CenovnikDTO getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(CenovnikDTO cenovnik) {
		this.cenovnik = cenovnik;
	}

	public RobaUslugaDto getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUslugaDto robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	public float getCenaSaPdv() {
		return cenaSaPdv;
	}

	public void setCenaSaPdv(float cenaSaPdv) {
		this.cenaSaPdv = cenaSaPdv;
	}
}
