package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.NotNull;

public class PDV_Dto {

	private long id;
	@NotNull
	private String nazivPDV;

	public PDV_Dto() {
	}

	public PDV_Dto(String nazivPDV) {
		super();
		this.nazivPDV = nazivPDV;

	}

	public long getId() {
		return id;
	}

	public PDV_Dto setId(long id) {
		this.id = id;
		return this;
	}

	public String getNazivPDV() {
		return nazivPDV;
	}

	public PDV_Dto setNazivPDV(String nazivPDV) {
		this.nazivPDV = nazivPDV;
		return this;
	}

}
