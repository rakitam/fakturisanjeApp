package sf.posinf.fakturisanje.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Preduzece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
    @NotNull
    @Size(max = 50)
	private String naziv;
    
    @NotNull
    @Size(max = 100)
	private String adresaPreduzeca;
    
    @NotNull
    @Size(min = 8, max = 8)
    private String PIB;
    
    @NotNull
    @Size(min = 10, max = 20)
	private String telefon;
    
    @NotNull
    @Size(max = 60)
	private String email;
    
    @NotNull
    @Size(max = 18)
    private String tekuciRacun;
    
	private String logo;
	
    @OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
    private Set<Faktura> fakture = new HashSet<>();
    
    @OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<PoslovniPartner> poslovniPartneri = new HashSet<>();
    
	@OneToMany( cascade = CascadeType.ALL)
	private Set<Cenovnik> cenovnici = new HashSet<>();
	
    @OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<GrupaRobe> grupeRobe = new HashSet<>();
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mesto_id")
    private Mesto mesto;
    
	@NotNull
	private boolean obrisano;
	
	public Preduzece() {
		
	}
	
	public Preduzece(String naziv, String adresaPreduzeca, String pIB, String telefon, String email,
			String tekuciRacun, String logo, Set<Faktura> fakture, Set<PoslovniPartner> poslovniPartneri, Set<Cenovnik> cenovnici,
			Set<GrupaRobe> grupeRobe, Mesto mesto) {
		super();
		this.naziv = naziv;
		this.adresaPreduzeca = adresaPreduzeca;
		PIB = pIB;
		this.telefon = telefon;
		this.email = email;
		this.tekuciRacun = tekuciRacun;
		this.logo = logo;
		this.fakture = fakture;
		this.poslovniPartneri = poslovniPartneri;
		this.cenovnici = cenovnici;
		this.grupeRobe = grupeRobe;
		this.mesto = mesto;
		this.obrisano = false;
	}	

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public Preduzece setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
		return this;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public Preduzece setNaziv(String naziv) {
		this.naziv = naziv;
		return this;
	}
	public String getAdresaPreduzeca() {
		return adresaPreduzeca;
	}
	public Preduzece setAdresaPreduzeca(String adresaPreduzeca) {
		this.adresaPreduzeca = adresaPreduzeca;
		return this;
	}
	public String getPIB() {
		return PIB;
	}
	public Preduzece setPIB(String pIB) {
		PIB = pIB;
		return this;
	}
	public String getTelefon() {
		return telefon;
	}
	public Preduzece setTelefon(String telefon) {
		this.telefon = telefon;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Preduzece setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getLogo() {
		return logo;
	}
	public Preduzece setLogo(String logo) {
		this.logo = logo;
		return this;
	}
	public Set<Faktura> getFakture() {
		return fakture;
	}
	public Preduzece setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
		return this;
	}
	public Set<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}
	public Preduzece setPoslovniPartneri(Set<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
		return this;
	}
	public Set<Cenovnik> getCenovnici() {
		return cenovnici;
	}
	public Preduzece setCenovnici(Set<Cenovnik> cenovnici) {
		this.cenovnici = cenovnici;
		return this;
	}
	public Set<GrupaRobe> getGrupeRobe() {
		return grupeRobe;
	}
	public Preduzece setGrupeRobe(Set<GrupaRobe> grupeRobe) {
		this.grupeRobe = grupeRobe;
		return this;
	}
	public Mesto getMesto() {
		return mesto;
	}
	public Preduzece setMesto(Mesto mesto) {
		this.mesto = mesto;
		return this;
	}

    public boolean isObrisano() {
        return obrisano;
    }

    public void setObrisano(boolean obrisano) {
        this.obrisano = obrisano;
    }
}
