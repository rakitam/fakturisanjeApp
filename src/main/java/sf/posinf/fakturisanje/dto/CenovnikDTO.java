package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CenovnikDTO {
	
	private long id;
	@NotNull
	private Date datumVazenja;
	@NotNull
	private PreduzeceDto preduzece;
	private boolean aktivan;
	
	public CenovnikDTO() {
		
	}

	public CenovnikDTO(long id, Date datumVazenja, PreduzeceDto preduzece, boolean aktivan) {
		this.id = id;
		this.datumVazenja = datumVazenja;
		this.preduzece = preduzece;
		this.aktivan = aktivan;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public PreduzeceDto getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(PreduzeceDto preduzece) {
		this.preduzece = preduzece;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
}
