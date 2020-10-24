package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class StopaPDV {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    @NotNull
    private float procenat;
    
    @NotNull
    private Date datumVazenja;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pdv_id")
    private PDV pdv;   

    public StopaPDV() {
    	
    }

    public StopaPDV(float procenat, Date datumVazenja, PDV pdv) {
		super();
		this.procenat = procenat;
		this.datumVazenja = datumVazenja;
		this.pdv = pdv;
    }

	public long getId() {
        return id;
    }

    public StopaPDV setId(long id) {
        this.id = id;
        return this;
    }

    public float getProcenat() {
        return procenat;
    }

    public StopaPDV setProcenat(float procenat) {
        this.procenat = procenat;
        return this;
    }

    public Date getDatumVazenja() {
        return datumVazenja;
    }

    public StopaPDV setDatumVazenja(Date datumVazenja) {
        this.datumVazenja = datumVazenja;
        return this;
    }

	public PDV getPdv() {
		return pdv;
	}

	public StopaPDV setPdv(PDV pdv) {
		this.pdv = pdv;
		return this;
	}	
}
