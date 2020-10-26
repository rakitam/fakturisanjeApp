package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.NotNull;

public class StavkaFaktureDto {

	private long id;
    @NotNull
	private long kolicina;
    @NotNull
	private float jedinicnaCena;
    @NotNull
	private double rabat;
    @NotNull
	private double osnovicaZaPdv;
    @NotNull
	private double procenatPdva;
    @NotNull
	private double iznosPdva;
    @NotNull
	private double iznosStavke;
    @NotNull
	private long faktura;
    @NotNull
	private long robaUsluga;
    
    public StavkaFaktureDto() {
    	
    }

	public StavkaFaktureDto(long id, @NotNull long kolicina, @NotNull float jedinicnaCena, @NotNull double rabat,
			@NotNull double osnovicaZaPdv, @NotNull double procenatPdva, @NotNull double iznosPdva,
			@NotNull double iznosStavke, @NotNull long faktura, @NotNull long robaUsluga) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.rabat = rabat;
		this.osnovicaZaPdv = osnovicaZaPdv;
		this.procenatPdva = procenatPdva;
		this.iznosPdva = iznosPdva;
		this.iznosStavke = iznosStavke;
		this.faktura = faktura;
		this.robaUsluga = robaUsluga;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKolicina() {
		return kolicina;
	}

	public void setKolicina(long kolicina) {
		this.kolicina = kolicina;
	}

	public float getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(float jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public double getRabat() {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public double getOsnovicaZaPdv() {
		return osnovicaZaPdv;
	}

	public void setOsnovicaZaPdv(double osnovicaZaPdv) {
		this.osnovicaZaPdv = osnovicaZaPdv;
	}

	public double getProcenatPdva() {
		return procenatPdva;
	}

	public void setProcenatPdva(double procenatPdva) {
		this.procenatPdva = procenatPdva;
	}

	public double getIznosPdva() {
		return iznosPdva;
	}

	public void setIznosPdva(double iznosPdva) {
		this.iznosPdva = iznosPdva;
	}

	public double getIznosStavke() {
		return iznosStavke;
	}

	public void setIznosStavke(double iznosStavke) {
		this.iznosStavke = iznosStavke;
	}

	public long getFaktura() {
		return faktura;
	}

	public void setFaktura(long faktura) {
		this.faktura = faktura;
	}

	public long getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(long robaUsluga) {
		this.robaUsluga = robaUsluga;
	}    
}
