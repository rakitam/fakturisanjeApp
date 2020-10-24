package sf.posinf.fakturisanje.model;

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
import javax.validation.constraints.Size;


@Entity
public class RobaUsluga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	private String nazivRobeUsluge;	
	
	@NotNull
	@Size(max = 12)
	private String jedinicaMere;  
	
	@OneToMany(mappedBy = "robaUsluga", cascade = CascadeType.ALL)
	private Set<StavkaFakture> stavkeFakture = new HashSet<>();
	
	@OneToMany(mappedBy = "robaUsluga", cascade = CascadeType.ALL)
	private Set<StavkaCenovnika> stavkeCenovnika = new HashSet<>(); 
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grupa_robe_id")
	private GrupaRobe grupaRobe;
	
	public RobaUsluga() {
		
	}

	public RobaUsluga(long id, @NotNull @Size(max = 50) String nazivRobeUsluge,
			@NotNull @Size(max = 12) String jedinicaMere, Set<StavkaFakture> stavkeFakture,
			Set<StavkaCenovnika> stavkeCenovnika, GrupaRobe grupaRobe) {
		super();
		this.id = id;
		this.nazivRobeUsluge = nazivRobeUsluge;
		this.jedinicaMere = jedinicaMere;
		this.stavkeFakture = stavkeFakture;
		this.stavkeCenovnika = stavkeCenovnika;
		this.grupaRobe = grupaRobe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivRobeUsluge() {
		return nazivRobeUsluge;
	}

	public void setNazivRobeUsluge(String nazivRobeUsluge) {
		this.nazivRobeUsluge = nazivRobeUsluge;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public Set<StavkaFakture> getStavkeFakture() {
		return stavkeFakture;
	}

	public void setStavkeFakture(Set<StavkaFakture> stavkeFakture) {
		this.stavkeFakture = stavkeFakture;
	}

	public Set<StavkaCenovnika> getStavkeCenovnika() {
		return stavkeCenovnika;
	}

	public void setStavkeCenovnika(Set<StavkaCenovnika> stavkeCenovnika) {
		this.stavkeCenovnika = stavkeCenovnika;
	}

	public GrupaRobe getGrupaRobe() {
		return grupaRobe;
	}

	public void setGrupaRobe(GrupaRobe grupaRobe) {
		this.grupaRobe = grupaRobe;
	}	
}
