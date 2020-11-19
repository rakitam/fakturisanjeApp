package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class CenovnikDTO {
	
	private long id;
	@NotNull
	private Date datumVazenja;
	@NotNull
	private long preduzece;
	
	public CenovnikDTO() {
		
	}

	public CenovnikDTO(long id, Date datumVazenja, @javax.validation.constraints.NotNull long preduzece) {
		this.id = id;
		this.datumVazenja = datumVazenja;
		this.preduzece = preduzece;
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

	public long getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(long preduzece) {
		this.preduzece = preduzece;
	}
}
