package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.NotNull;


public class GrupaRobeDto {
	
	private long id;
    @NotNull
    private String nazivGrupe;
    @NotNull
    private long preduzece;
    @NotNull
    private PDV_Dto pdv;

    public GrupaRobeDto() { }


	public GrupaRobeDto(String nazivGrupe,long preduzece, PDV_Dto pdv) {
		super();
		this.nazivGrupe = nazivGrupe;
		this.preduzece = preduzece;
		this.pdv = pdv;
	}

	public long getId() {
		return id;
	}

	public GrupaRobeDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getNazivGrupe() {
		return nazivGrupe;
	}

	public GrupaRobeDto setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
		return this;
	}

	public long getPreduzece() {
		return preduzece;
	}

	public GrupaRobeDto setPreduzece(long preduzece) {
		this.preduzece = preduzece;
		return this;
	}

	public PDV_Dto getPdv() {
		return pdv;
	}

	public GrupaRobeDto setPdv(PDV_Dto pdv) {
		this.pdv = pdv;
		return this;
	}
	
}
