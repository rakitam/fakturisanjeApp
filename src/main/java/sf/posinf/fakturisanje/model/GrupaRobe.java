package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
public class GrupaRobe {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
	@NotNull
    @Size(max = 50)
    private String nazivGrupe;
    
	@OneToMany(mappedBy = "grupaRobe", cascade = CascadeType.ALL)
    private Set<RobaUsluga> robaUsluge = new HashSet<>();
    
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preduzece_id")
    private Preduzece preduzece;
    
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pdv_id")
    private PDV pdv;

	private boolean obrisano;
	
	public GrupaRobe() {
		
	}

	public GrupaRobe(long id, @NotNull @Size(max = 50) String nazivGrupe, Set<RobaUsluga> robaUsluge,
			Preduzece preduzece, PDV pdv) {
		super();
		this.id = id;
		this.nazivGrupe = nazivGrupe;
		this.robaUsluge = robaUsluge;
		this.preduzece = preduzece;
		this.pdv = pdv;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivGrupe() {
		return nazivGrupe;
	}

	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}

	public Set<RobaUsluga> getRobaUsluge() {
		return robaUsluge;
	}

	public void setRobaUsluge(Set<RobaUsluga> robaUsluge) {
		this.robaUsluge = robaUsluge;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public PDV getPdv() {
		return pdv;
	}

	public void setPdv(PDV pdv) {
		this.pdv = pdv;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
}
