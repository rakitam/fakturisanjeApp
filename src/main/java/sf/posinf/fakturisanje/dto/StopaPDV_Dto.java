package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class StopaPDV_Dto {

	private long id;
	@NotNull
	private float procenat;
	@NotNull
	private Date datumVazenja;
	@NotNull
	private PDV_Dto pdv;
	private boolean active = true;

	public StopaPDV_Dto() {
	}

	public StopaPDV_Dto(long id, @NotNull float procenat, @NotNull Date datumVazenja, @NotNull PDV_Dto pdv, boolean active) {
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

	public PDV_Dto getPdv() {
		return pdv;
	}

	public void setPdv(PDV_Dto pdv) {
		this.pdv = pdv;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
