package sf.posinf.fakturisanje.dto;

import com.sun.istack.NotNull;
import java.util.Date;

public class CenovnikDTO {
	
	private long id;
	@NotNull
	private Date datumVazenja;
	
	public CenovnikDTO() {
		
	}
	
	public CenovnikDTO(Date datumVazenja) {
		super();
		this.datumVazenja = datumVazenja;
	
		
	}

	public long getId() {
		return id;
	}

	public CenovnikDTO setId(long id) {
		this.id = id;
		return this;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public CenovnikDTO setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
		return this;
	}

	


}
