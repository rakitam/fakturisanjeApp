package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PoslovnaGodina {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    @NotNull
    @Min(2000)
    @Max(3000)
    private int godina;
    
    private boolean zakljucana;
    
    @OneToMany(mappedBy = "poslovnaGodina")
    private Set<Faktura> fakture = new HashSet<>();

    public PoslovnaGodina() {
    	
    }   

	public PoslovnaGodina(long id, @NotNull @Min(2000) @Max(3000) int godina, boolean zakljucana,
			Set<Faktura> fakture) {
		super();
		this.id = id;
		this.godina = godina;
		this.zakljucana = zakljucana;
		this.fakture = fakture;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public boolean isZakljucana() {
		return zakljucana;
	}

	public void setZakljucana(boolean zakljucana) {
		this.zakljucana = zakljucana;
	}

	public Set<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(Set<Faktura> fakture) {
		this.fakture = fakture;
	}    
}
