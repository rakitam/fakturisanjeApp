package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PDV {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    @NotNull
	@Size(max = 30)
    private String nazivPDV;
    
	@OneToMany(mappedBy = "pdv")
	private Set<StopaPDV> stopePdv = new HashSet<>();
	
	@OneToMany(mappedBy = "pdv")
    private Set<GrupaRobe> grupeRobe = new HashSet<>();


    public PDV() { }

	public PDV(String nazivPDV, Set<StopaPDV> stopePdv, Set<GrupaRobe> grupeRobe) {
		super();
		this.nazivPDV = nazivPDV;
		this.stopePdv = stopePdv;
		this.grupeRobe = grupeRobe;
		
	}

	public long getId() {
        return id;
    }

    public PDV setId(long id) {
        this.id = id;
        return this;
    }

    public String getNazivPDV() {
        return nazivPDV;
    }

    public PDV setNazivPDV(String nazivPDV) {
        this.nazivPDV = nazivPDV;
        return this;
    }

	public Set<StopaPDV> getStopePdv() {
		return stopePdv;
	}

	public Set<GrupaRobe> getGrupeRobe() {
		return grupeRobe;
	}

	public PDV setStopePdv(Set<StopaPDV> stopePdv) {
		this.stopePdv = stopePdv;
		return this;
	}

	public PDV setGrupeRobe(Set<GrupaRobe> grupeRobe) {
		this.grupeRobe = grupeRobe;
		return this;
	}
}
