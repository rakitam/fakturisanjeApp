package sf.posinf.fakturisanje.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cenovnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private Date datumVazenja;
	
    @OneToMany(mappedBy = "cenovnik", cascade = CascadeType.ALL)
	private Set<StavkaCenovnika> stavkeCenovnika = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;
    
    @NotNull
   	private boolean obrisano;
    
	public Cenovnik() {
		
	}

	public Cenovnik(long id, Date datumVazenja, Set<StavkaCenovnika> stavkeCenovnika, Preduzece preduzece, boolean obrisano) {
		this.id = id;
		this.datumVazenja = datumVazenja;
		this.stavkeCenovnika = stavkeCenovnika;
		this.preduzece = preduzece;
		this.obrisano = obrisano;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public Set<StavkaCenovnika> getStavkeCenovnika() {
		return stavkeCenovnika;
	}

	public void setStavkeCenovnika(Set<StavkaCenovnika> stavkeCenovnika) {
		this.stavkeCenovnika = stavkeCenovnika;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}
}
