package sf.posinf.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PoslovniPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(max = 50)
    private String nazivPartnera;
    @NotNull
    @Size(max = 50)
    private String adresa;
    @Min(0)
    @Max(1)
    private int vrstaPartnera; //0 - kupac, 1 - prodavac
    @NotNull
    @Size(min = 18, max = 18)
    private String tekuciRacun;
    @NotNull
    @Size(min = 8, max = 8)
    private String PIB;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preduzece_id")
    private Preduzece preduzece;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mesto_id")
    private Mesto mesto;
    @OneToMany(mappedBy = "poslovniPartner", cascade = CascadeType.ALL)
    private Set<Faktura> fakture = new HashSet<>();
    @NotNull
    private boolean obrisano;
    @OneToMany(cascade = CascadeType.ALL)
	private Set<Cenovnik> cenovnici = new HashSet<>();
    

    public PoslovniPartner() { }

	public PoslovniPartner(String nazivPartnera, String adresa, int vrstaPartnera, String tekuciRacun,
			String pIB, Preduzece preduzece, Mesto mesto, Set<Faktura> fakture, Set<Cenovnik> cenovnici) {
		super();
		this.nazivPartnera = nazivPartnera;
		this.adresa = adresa;
		this.vrstaPartnera = vrstaPartnera;
		this.tekuciRacun = tekuciRacun;
		this.PIB = pIB;
		this.preduzece = preduzece;
		this.mesto = mesto;
		this.fakture = fakture;
		this.obrisano = false;
		this.cenovnici = cenovnici;
	}
	

	public Set<Cenovnik> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(Set<Cenovnik> cenovnici) {
		this.cenovnici = cenovnici;
	}

	public String getPIB() {
		return PIB;
	}

	public PoslovniPartner setPIB(String pIB) {
		PIB = pIB;
		return this;
	}

	public long getId() {
        return id;
    }

    public PoslovniPartner setId(long id) {
        this.id = id;
        return this;
    }

    public String getNazivPartnera() {
        return nazivPartnera;
    }

    public PoslovniPartner setNazivPartnera(String nazivPartnera) {
        this.nazivPartnera = nazivPartnera;
        return this;
    }

    public String getAdresa() {
        return adresa;
    }

    public PoslovniPartner setAdresa(String adresa) {
        this.adresa = adresa;
        return this;
    }

    public int getVrstaPartnera() {
        return vrstaPartnera;
    }

    public PoslovniPartner setVrstaPartnera(int vrstaPartnera) {
        this.vrstaPartnera = vrstaPartnera;
        return this;
    }

    public String getTekuciRacun() {
        return tekuciRacun;
    }

    public PoslovniPartner setTekuciRacun(String tekuciRacun) {
        this.tekuciRacun = tekuciRacun;
        return this;
    }

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public Set<Faktura> getFakture() {
		return fakture;
	}

	public PoslovniPartner setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
		return this;
	}

	public PoslovniPartner setMesto(Mesto mesto) {
		this.mesto = mesto;
		return this;
	}

	public PoslovniPartner setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
		return this;
	}

    public boolean isObrisano() {
        return obrisano;
    }

    public void setObrisano(boolean obrisano) {
        this.obrisano = obrisano;
    }
}
