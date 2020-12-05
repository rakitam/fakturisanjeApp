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
    
    @ManyToOne
    @JoinColumn(name = "pdv_id")
    private PDV pdv;

    @NotNull
    private boolean active;

    public StopaPDV() {
    	
    }

    public StopaPDV(long id, @NotNull float procenat, @NotNull Date datumVazenja, PDV pdv, boolean active) {
        this.id = id;
        this.procenat = procenat;
        this.datumVazenja = datumVazenja;
        this.pdv = pdv;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getProcenat() {
        return procenat;
    }

    public void setProcenat(float procenat) {
        this.procenat = procenat;
    }

    public Date getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(Date datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public PDV getPdv() {
        return pdv;
    }

    public void setPdv(PDV pdv) {
        this.pdv = pdv;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
