package sf.posinf.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	//TODO: Bice otkomentarisano kad se dodaju odgovarajuce klase
    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "preduzece_id")
	//private Preduzece preduzece;
    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "poslovni_partner_id")
	//private PoslovniPartner poslovniPartner;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poslovna_godina_id")
	private PoslovnaGodina poslovnaGodina;
    @OneToMany(mappedBy = "faktura", cascade = CascadeType.ALL)
    private Set<StavkaFakture> stavkeFakture = new HashSet<>();
	@NotNull
	private boolean obrisana;
	
	public Faktura() {
		
	}
	
	//TODO: Dodati konstruktor, getere i setere kada se dodaju preostale klase
}
