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
	private Set<PoslovniPartner> poslovniPartneri = new HashSet<>();
    
    @OneToMany(mappedBy = "mesto", cascade = CascadeType.ALL)
	private Set<Preduzece> preduzeca = new HashSet<>();
    
	@NotNull
	private boolean obrisano;
	
	public Mesto() { }
	
	public Mesto(String naziv, int postanskiBroj, String drzava, Set<PoslovniPartner> poslovniPartneri,
			Set<Preduzece> preduzeca) {
		super();
		this.naziv = naziv;
		this.postanskiBroj = postanskiBroj;
		this.drzava = drzava;
		this.poslovniPartneri = poslovniPartneri;
		this.preduzeca = preduzeca;
		this.obrisano = false;
	}
	
	public long getId() {
		return id;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public int getPostanskiBroj() {
		return postanskiBroj;
	}
	
	public String getDrzava() {
		return drzava;
	}
	
	public Set<PoslovniPartner> getPoslovniPartneri() {
		return poslovniPartneri;
	}
	
	public Set<Preduzece> getPreduzeca() {
		return preduzeca;
	}
	
	public Mesto setId(long id) {
		this.id = id;
		return this;
	}
	
	public Mesto setNaziv(String naziv) {
		this.naziv = naziv;
		return this;
	}
	
	public Mesto setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
		return this;
	}
	
	public Mesto setDrzava(String drzava) {
		this.drzava = drzava;
		return this;
	}
	
	public Mesto setPoslovniPartneri(Set<PoslovniPartner> poslovniPartneri) {
		this.poslovniPartneri = poslovniPartneri;
		return this;
	}
	
	public Mesto setPreduzeca(Set<Preduzece> preduzeca) {
		this.preduzeca = preduzeca;
		return this;
	}

    public boolean isObrisano() {
        return obrisano;
    }

    public void setObrisano(boolean obrisano) {
        this.obrisano = obrisano;
    }
}
