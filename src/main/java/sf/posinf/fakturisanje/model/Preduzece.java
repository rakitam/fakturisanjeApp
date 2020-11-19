package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Korisnik> korisnici = new HashSet<>();
	
    @OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
    private Set<Faktura> fakture = new HashSet<>();
    
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

	public Preduzece(long id, @NotNull @Size(max = 50) String naziv, @NotNull @Size(max = 100) String adresaPreduzeca,
			@NotNull @Size(min = 8, max = 8) String pIB, @NotNull @Size(min = 10, max = 20) String telefon,
			@NotNull @Size(max = 60) String email, @NotNull @Size(max = 18) String tekuciRacun, String logo,
			Set<Korisnik> korisnici, Set<Faktura> fakture, Set<Cenovnik> cenovnici, Set<GrupaRobe> grupeRobe,
			Mesto mesto, @NotNull boolean obrisano) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresaPreduzeca = adresaPreduzeca;
		PIB = pIB;
		this.telefon = telefon;
		this.email = email;
		this.tekuciRacun = tekuciRacun;
		this.logo = logo;
		this.korisnici = korisnici;
		this.fakture = fakture;
		this.cenovnici = cenovnici;
		this.grupeRobe = grupeRobe;
		this.mesto = mesto;
		this.obrisano = obrisano;
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

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresaPreduzeca() {
		return adresaPreduzeca;
	}

	public void setAdresaPreduzeca(String adresaPreduzeca) {
		this.adresaPreduzeca = adresaPreduzeca;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public void setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Set<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(Set<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	public Set<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
	}

	public Set<Cenovnik> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(Set<Cenovnik> cenovnici) {
		this.cenovnici = cenovnici;
	}

	public Set<GrupaRobe> getGrupeRobe() {
		return grupeRobe;
	}

	public void setGrupeRobe(Set<GrupaRobe> grupeRobe) {
		this.grupeRobe = grupeRobe;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}	
}
