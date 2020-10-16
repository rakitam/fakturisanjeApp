package sf.posinf.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
    
	//@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "preduzece_id")
    //private Preduzece preduzece;
    
	//@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "pdv_id")
    //private PDV pdv;
	
	//geteri i seteri ce biti dodati kada se sve klase dodaju
	
}
