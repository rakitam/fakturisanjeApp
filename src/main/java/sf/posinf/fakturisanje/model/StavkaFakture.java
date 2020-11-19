package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class StavkaFakture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	private boolean obrisana;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "faktura_id")
	private Faktura faktura;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roba_usluga_id")
	private RobaUsluga robaUsluga;
	
	public StavkaFakture() {
		
	}

	public StavkaFakture(long id, @NotNull long kolicina, @NotNull float jedinicnaCena, @NotNull double rabat,
			@NotNull double osnovicaZaPdv, @NotNull double procenatPdva, @NotNull double iznosPdva,
			@NotNull double iznosStavke, boolean obrisana, Faktura faktura, RobaUsluga robaUsluga) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.jedinicnaCena = jedinicnaCena;
		this.rabat = rabat;
		this.osnovicaZaPdv = osnovicaZaPdv;
		this.procenatPdva = procenatPdva;
		this.iznosPdva = iznosPdva;
		this.iznosStavke = iznosStavke;
		this.obrisana = obrisana;
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

	public boolean isObrisana() {
		return obrisana;
	}

	public void setObrisana(boolean obrisana) {
		this.obrisana = obrisana;
	}

	public Faktura getFaktura() {
		return faktura;
	}

	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}

	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}	
}
