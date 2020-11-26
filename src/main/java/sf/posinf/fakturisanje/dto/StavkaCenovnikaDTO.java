package sf.posinf.fakturisanje.dto;

import com.sun.istack.NotNull;

public class StavkaCenovnikaDTO {

	private long id;
	@NotNull
	private float cena;
	@NotNull
	private long cenovnik;
	@NotNull
	private RobaUslugaDto robaUsluga;

	public StavkaCenovnikaDTO() {
	}

	public StavkaCenovnikaDTO(float cena, long cenovnik, RobaUslugaDto robaUsluga) {
		super();
		this.cena = cena;
		this.cenovnik = cenovnik;
		this.robaUsluga = robaUsluga;
	}

	public long getId() {
		return id;
	}

	public float getCena() {
		return cena;
	}

	public StavkaCenovnikaDTO setCena(float cena) {
		this.cena = cena;
		return this;
	}

	public long getCenovnik() {
		return cenovnik;
	}

	public StavkaCenovnikaDTO setCenovnik(long cenovnik) {
		this.cenovnik = cenovnik;
		return this;
	}

	public RobaUslugaDto getRobaUsluga() {
		return robaUsluga;
	}

	public StavkaCenovnikaDTO setRobaUsluga(RobaUslugaDto robaUsluga) {
		this.robaUsluga = robaUsluga;
		return this;
	}

	public StavkaCenovnikaDTO setId(long id) {
		this.id = id;
		return this;
	}

}
