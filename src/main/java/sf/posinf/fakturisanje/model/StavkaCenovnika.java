package sf.posinf.fakturisanje.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class StavkaCenovnika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    private float cena;
    
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cenovnik_id")
	private Cenovnik cenovnik;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roba_usluga_id")
    private RobaUsluga robaUsluga;
	
	@NotNull
	private boolean obrisano;

    public StavkaCenovnika(){ }
    

    public StavkaCenovnika(float cena, Cenovnik cenovnik, RobaUsluga robaUsluga, boolean obrisano) {
		super();
		this.cena = cena;
		this.cenovnik = cenovnik;
		this.robaUsluga = robaUsluga;
		this.obrisano = obrisano;
	}


	public long getId() {
        return id;
    }

	public StavkaCenovnika setId(long id) {
        this.id = id;
        return this;
    }

    public float getCena() {
        return cena;
    }

    public StavkaCenovnika setCena(float cena) {
        this.cena = cena;
        return this;
    }

	public Cenovnik getCenovnik() {
		return cenovnik;
	}


	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public StavkaCenovnika setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
		return this;
	}

	public StavkaCenovnika setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
		return this;
	}


	public boolean isObrisano() {
		return obrisano;
	}


	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}


}
