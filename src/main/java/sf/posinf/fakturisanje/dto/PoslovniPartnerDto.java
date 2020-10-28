package sf.posinf.fakturisanje.dto;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class PoslovniPartnerDto {

    private long id;
    @NotNull
    private String nazivPartnera;
    @NotNull
    private String adresa;
    @NotNull
    private int vrstaPartnera;
    @NotNull
    @Size(max = 18)
    private String tekuciRacun;
    @NotNull
    @Size(min = 8, max = 8)
    private String PIB;
    @NotNull
    private long preduzece;
    @NotNull
    private long mesto;
   
 
    public PoslovniPartnerDto() { }

	public PoslovniPartnerDto(String nazivPartnera, String adresa, int vrstaPartnera, String tekuciRacun,
			String pIB, long preduzece, long mesto) {
		super();
		this.nazivPartnera = nazivPartnera;
		this.adresa = adresa;
		this.vrstaPartnera = vrstaPartnera;
		this.tekuciRacun = tekuciRacun;
		this.PIB = pIB;
		this.preduzece = preduzece;
		this.mesto = mesto;

	}
	

	public String getPIB() {
		return PIB;
	}

	public PoslovniPartnerDto setPIB(String pIB) {
		PIB = pIB;
		return this;
	}

	public long getId() {
		return id;
	}

	public PoslovniPartnerDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getNazivPartnera() {
		return nazivPartnera;
	}

	public PoslovniPartnerDto setNazivPartnera(String nazivPartnera) {
		this.nazivPartnera = nazivPartnera;
		return this;
	}

	public String getAdresa() {
		return adresa;
	}

	public PoslovniPartnerDto setAdresa(String adresa) {
		this.adresa = adresa;
		return this;
	}

	public int getVrstaPartnera() {
		return vrstaPartnera;
	}

	public PoslovniPartnerDto setVrstaPartnera(int vrstaPartnera) {
		this.vrstaPartnera = vrstaPartnera;
		return this;
	}

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public PoslovniPartnerDto setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
		return this;
	}

	public long getPreduzece() {
		return preduzece;
	}

	public PoslovniPartnerDto setPreduzece(long preduzece) {
		this.preduzece = preduzece;
		return this;
	}

	public long getMesto() {
		return mesto;
	}

	public PoslovniPartnerDto setMesto(long mesto) {
		this.mesto = mesto;
		return this;
	}

}