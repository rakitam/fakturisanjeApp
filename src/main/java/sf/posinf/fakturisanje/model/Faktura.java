package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Faktura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//Broj fakture mora biti u formatu id/poslovnaGodina
	private long brojFakture;
	
	private Date datumFakture;
	
	private Date datumValute;

	private Date datumStorniranja;

	@Enumerated(EnumType.STRING)
	private StatusFakture statusFakture;
	
    @NotNull
	private double osnovica;
    
    @NotNull
	private double rabat;
    
    @NotNull
	private double ukupanPdv;
    
    @NotNull
	private double iznosBezRabata;
    
    @NotNull
	private double iznosZaPlacanje;
	
    @ManyToOne
    @JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;
    
    @ManyToOne
    @JoinColumn(name = "poslovna_godina_id")
	private PoslovnaGodina poslovnaGodina;
    
    @OneToMany(mappedBy = "faktura")
    private Set<StavkaFakture> stavkeFakture = new HashSet<>();
    
    @ManyToOne
	@JoinColumn(name = "korisnik_id")
	private Korisnik korisnik;
	
	public Faktura() {
		
	}

	public Faktura(long id, long brojFakture, Date datumFakture, Date datumValute, StatusFakture statusFakture,
			@NotNull double osnovica, @NotNull double rabat, @NotNull double ukupanPdv, @NotNull double iznosBezRabata,
			@NotNull double iznosZaPlacanje, boolean obrisana, Preduzece preduzece, PoslovnaGodina poslovnaGodina,
			Set<StavkaFakture> stavkeFakture, Korisnik korisnik) {
		super();
		this.id = id;
		this.brojFakture = brojFakture;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.statusFakture = statusFakture;
		this.osnovica = osnovica;
		this.rabat = rabat;
		this.ukupanPdv = ukupanPdv;
		this.iznosBezRabata = iznosBezRabata;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.preduzece = preduzece;
		this.poslovnaGodina = poslovnaGodina;
		this.stavkeFakture = stavkeFakture;
		this.korisnik = korisnik;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBrojFakture() {
		return brojFakture;
	}

	public void setBrojFakture(long brojFakture) {
		this.brojFakture = brojFakture;
	}

	public Date getDatumFakture() {
		return datumFakture;
	}

	public void setDatumFakture(Date datumFakture) {
		this.datumFakture = datumFakture;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public StatusFakture getStatusFakture() {
		return statusFakture;
	}

	public void setStatusFakture(StatusFakture statusFakture) {
		this.statusFakture = statusFakture;
	}

	public double getOsnovica() {
		return osnovica;
	}

	public void setOsnovica(double osnovica) {
		this.osnovica = osnovica;
	}

	public double getRabat() {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public double getUkupanPdv() {
		return ukupanPdv;
	}

	public void setUkupanPdv(double ukupanPdv) {
		this.ukupanPdv = ukupanPdv;
	}

	public double getIznosBezRabata() {
		return iznosBezRabata;
	}

	public void setIznosBezRabata(double iznosBezRabata) {
		this.iznosBezRabata = iznosBezRabata;
	}

	public double getIznosZaPlacanje() {
		return iznosZaPlacanje;
	}

	public void setIznosZaPlacanje(double iznosZaPlacanje) {
		this.iznosZaPlacanje = iznosZaPlacanje;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public Set<StavkaFakture> getStavkeFakture() {
		return stavkeFakture;
	}

	public void setStavkeFakture(Set<StavkaFakture> stavkeFakture) {
		this.stavkeFakture = stavkeFakture;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Date getDatumStorniranja() {
		return datumStorniranja;
	}

	public void setDatumStorniranja(Date datumStorniranja) {
		this.datumStorniranja = datumStorniranja;
	}
}
