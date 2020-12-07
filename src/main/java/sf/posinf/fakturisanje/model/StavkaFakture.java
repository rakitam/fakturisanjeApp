package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class StavkaFakture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "roba_usluga_id")
	private RobaUsluga robaUsluga;

	@NotNull
	private float jedinicnaCena;
	
	@NotNull
	private long kolicina;
	
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
	
	@ManyToOne
	@JoinColumn(name = "faktura_id")
	private Faktura faktura;

	@NotNull
	private boolean obrisana;
	
	public StavkaFakture() {
		
	}

	public StavkaFakture(long id, RobaUsluga robaUsluga, @NotNull float jedinicnaCena, @NotNull long kolicina, @NotNull double rabat, @NotNull double osnovicaZaPdv, @NotNull double procenatPdva, @NotNull double iznosPdva, @NotNull double iznosStavke, Faktura faktura, @NotNull boolean obrisana) {
		this.id = id;
		this.robaUsluga = robaUsluga;
		this.jedinicnaCena = jedinicnaCena;
		this.kolicina = kolicina;
		this.rabat = rabat;
		this.osnovicaZaPdv = osnovicaZaPdv;
		this.procenatPdva = procenatPdva;
		this.iznosPdva = iznosPdva;
		this.iznosStavke = iznosStavke;
		this.faktura = faktura;
		this.obrisana = obrisana;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}

	public float getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(float jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public long getKolicina() {
		return kolicina;
	}

	public void setKolicina(long kolicina) {
		this.kolicina = kolicina;
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

	public Faktura getFaktura() {
		return faktura;
	}

	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}

	public boolean isObrisana() {
		return obrisana;
	}

	public void setObrisana(boolean obrisana) {
		this.obrisana = obrisana;
	}
}
