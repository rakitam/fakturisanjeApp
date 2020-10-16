package sf.posinf.model;

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
    
	//@OneToMany(mappedBy = "robaUsluga", cascade = CascadeType.ALL)
	//private Set<StavkaCenovnika> stavkeCenovnika = new HashSet<>();
    
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grupa_robe_id")
	private GrupaRobe grupaRobe;
	
	//geteri i seteri ce biti dodati kada se sve klase dodaju
	
	
}
