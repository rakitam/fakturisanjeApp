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

    private float cenaSaPdv;
    
	@ManyToOne
	@JoinColumn(name = "cenovnik_id")
	private Cenovnik cenovnik;
	
	@ManyToOne
	@JoinColumn(name = "roba_usluga_id")
    private RobaUsluga robaUsluga;

    public StavkaCenovnika(){ }

	public StavkaCenovnika(long id, @NotNull float cena, float cenaSaPdv, Cenovnik cenovnik, RobaUsluga robaUsluga) {
		this.id = id;
		this.cena = cena;
		this.cenaSaPdv = cenaSaPdv;
		this.cenovnik = cenovnik;
		this.robaUsluga = robaUsluga;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public float getCenaSaPdv() {
		return cenaSaPdv;
	}

	public void setCenaSaPdv(float cenaSaPdv) {
		this.cenaSaPdv = cenaSaPdv;
	}

	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}

	public RobaUsluga getRobaUsluga() {
		return robaUsluga;
	}

	public void setRobaUsluga(RobaUsluga robaUsluga) {
		this.robaUsluga = robaUsluga;
	}
}
