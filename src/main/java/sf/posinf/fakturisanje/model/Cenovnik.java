package sf.posinf.fakturisanje.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Cenovnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private Date datumVazenja;
	
    @OneToMany(mappedBy = "cenovnik", cascade = CascadeType.ALL)
	private Set<StavkaCenovnika> stavkeCenovnika = new HashSet<>();
    
    @NotNull
   	private boolean obrisano;
    
	public Cenovnik() {
		
	}
	
	public Cenovnik(Date datumVazenja, Set<StavkaCenovnika> stavkeCenovnika) {
		super();
		this.datumVazenja = datumVazenja;
		this.stavkeCenovnika = stavkeCenovnika;
	}
	
	public long getId() {
		return id;
	}
	public Date getDatumVazenja() {
		return datumVazenja;
	}
	public Set<StavkaCenovnika> getStavkeCenovnika() {
		return stavkeCenovnika;
	}


	public Cenovnik setId(long id) {
		this.id = id;
		return this;
	}
	public Cenovnik setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
		return this;
	}
	public Cenovnik setStavkeCenovnika(Set<StavkaCenovnika> stavkeCenovnika) {
		this.stavkeCenovnika = stavkeCenovnika;
		return this;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}

}
