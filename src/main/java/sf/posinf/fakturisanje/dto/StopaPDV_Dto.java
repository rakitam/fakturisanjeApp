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
	private long pdv;

	public StopaPDV_Dto() {
	}

	public StopaPDV_Dto(float procenat, Date datumVazenja, long pdv) {
		super();
		this.procenat = procenat;
		this.datumVazenja = datumVazenja;
		this.pdv = pdv;
	}

	public long getId() {
		return id;
	}

	public StopaPDV_Dto setId(long id) {
		this.id = id;
		return this;
	}

	public float getProcenat() {
		return procenat;
	}

	public StopaPDV_Dto setProcenat(float procenat) {
		this.procenat = procenat;
		return this;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public StopaPDV_Dto setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
		return this;
	}

	public long getPdv() {
		return pdv;
	}

	public StopaPDV_Dto setPdv(long pdv) {
		this.pdv = pdv;
		return this;
	}

}
