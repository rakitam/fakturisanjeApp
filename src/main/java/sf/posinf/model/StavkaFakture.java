package sf.posinf.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class StavkaFakture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private long kolicina;
	@NotNull
	private float jedinicnaCena;
	@NotNull
	private double rabat;
	@NotNull
	private double osnovicaZaPdv;
	@NotNull
	private double procenatPdva;
	@NotNull
	private double iznosPdva;
	@NotNull
	private double iznosStavke;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "faktura_id")
	private Faktura faktura;
	//TODO: Bice otkomentarisano kad se doda odgovarajuca klasa
	//@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "roba_usluga_id")
	//private RobaUsluga robaUsluga;
	@NotNull
	private boolean obrisano;
	
	public StavkaFakture() {
		
	}
	
	//TODO: Dodati konstruktor, getere i setere kada se dodaju preostale klase
}
