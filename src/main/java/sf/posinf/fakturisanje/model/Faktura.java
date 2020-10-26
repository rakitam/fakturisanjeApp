package sf.posinf.fakturisanje.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Faktura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long brojFakture;
	
	private Date datumFakture;
	
	private Date datumValute;
	
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
    
	private boolean placeno;
	
	private boolean vrstaFakture; //true = ulazne fakture, false = izlazne fakture
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poslovni_partner_id")
	private PoslovniPartner poslovniPartner;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poslovna_godina_id")
	private PoslovnaGodina poslovnaGodina;
    
    @OneToMany(mappedBy = "faktura", cascade = CascadeType.ALL)
    private Set<StavkaFakture> stavkeFakture = new HashSet<>();
	
	public Faktura() {
		
	}

	public Faktura(long id, long brojFakture, Date datumFakture, Date datumValute, StatusFakture statusFakture,
			@NotNull double osnovica, @NotNull double rabat, @NotNull double ukupanPdv, @NotNull double iznosBezRabata,
			@NotNull double iznosZaPlacanje, boolean placeno, boolean vrstaFakture, Preduzece preduzece,
			PoslovniPartner poslovniPartner, PoslovnaGodina poslovnaGodina, Set<StavkaFakture> stavkeFakture) {
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
		this.placeno = placeno;
		this.vrstaFakture = vrstaFakture;
		this.preduzece = preduzece;
		this.poslovniPartner = poslovniPartner;
		this.poslovnaGodina = poslovnaGodina;
		this.stavkeFakture = stavkeFakture;
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

	public boolean isPlaceno() {
		return placeno;
	}

	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}

	public boolean isVrstaFakture() {
		return vrstaFakture;
	}

	public void setVrstaFakture(boolean vrstaFakture) {
		this.vrstaFakture = vrstaFakture;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public PoslovniPartner getPoslovniPartner() {
		return poslovniPartner;
	}

	public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
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
}