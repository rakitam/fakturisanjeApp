package sf.posinf.fakturisanje.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Mesto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	private String naziv;
	
	@Min(10000)
	private int postanskiBroj;
	
    @NotNull
	@Size(max = 40)
	private String drzava;
    
    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL)
	private Set<Preduzece> preduzeca = new HashSet<>();
    
	@NotNull
	private boolean obrisano;
	
	public Mesto() { 
		
	}
	
	public Mesto(long id, @NotNull @Size(max = 50) String naziv, @Min(10000) int postanskiBroj,
			@NotNull @Size(max = 40) String drzava, Set<Preduzece> preduzeca, @NotNull boolean obrisano) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.postanskiBroj = postanskiBroj;
		this.drzava = drzava;
		this.preduzeca = preduzeca;
		this.obrisano = obrisano;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public Set<Preduzece> getPreduzeca() {
		return preduzeca;
	}

	public void setPreduzeca(Set<Preduzece> preduzeca) {
		this.preduzeca = preduzeca;
	}

	public boolean isObrisano() {
		return obrisano;
	}

	public void setObrisano(boolean obrisano) {
		this.obrisano = obrisano;
	}	
}
