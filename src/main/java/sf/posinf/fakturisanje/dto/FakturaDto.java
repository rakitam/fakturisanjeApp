package sf.posinf.fakturisanje.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class FakturaDto {

	private long id;
	private long brojFakture;
	private Date datumFakture;
	private Date datumValute;
	private double iznosBezRabata;
	private double rabat;
	private double osnovica;
	private double ukupanPdv;
	private double iznosZaPlacanje;
	private boolean placeno;
	private boolean vrstaFakture;
	@NotNull
	private long preduzece;
	@NotNull
	private long poslovniPartner;
	private long poslovnaGodina;
	
	public FakturaDto() {
		
	}

	public FakturaDto(long id, long brojFakture, Date datumFakture, Date datumValute, double iznosBezRabata,
			double rabat, double osnovica, double ukupanPdv, double iznosZaPlacanje, boolean placeno,
			boolean vrstaFakture, @NotNull long preduzece, @NotNull long poslovniPartner, long poslovnaGodina) {
		super();
		this.id = id;
		this.brojFakture = brojFakture;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.iznosBezRabata = iznosBezRabata;
		this.rabat = rabat;
		this.osnovica = osnovica;
		this.ukupanPdv = ukupanPdv;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.placeno = placeno;
		this.vrstaFakture = vrstaFakture;
		this.preduzece = preduzece;
		this.poslovniPartner = poslovniPartner;
		this.poslovnaGodina = poslovnaGodina;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBrojFakture() {
		return brojFakture;
	}

	public void setBrojFakture(long brojFakture) {
		this.brojFakture = brojFakture;
	}

	public Date getDatumFakture() {
		return datumFakture;
	}

	public void setDatumFakture(Date datumFakture) {
		this.datumFakture = datumFakture;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public double getIznosBezRabata() {
		return iznosBezRabata;
	}

	public void setIznosBezRabata(double iznosBezRabata) {
		this.iznosBezRabata = iznosBezRabata;
	}

	public double getRabat() {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public double getOsnovica() {
		return osnovica;
	}

	public void setOsnovica(double osnovica) {
		this.osnovica = osnovica;
	}

	public double getUkupanPdv() {
		return ukupanPdv;
	}

	public void setUkupanPdv(double ukupanPdv) {
		this.ukupanPdv = ukupanPdv;
	}

	public double getIznosZaPlacanje() {
		return iznosZaPlacanje;
	}

	public void setIznosZaPlacanje(double iznosZaPlacanje) {
		this.iznosZaPlacanje = iznosZaPlacanje;
	}

	public boolean isPlaceno() {
		return placeno;
	}

	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}

	public boolean isVrstaFakture() {
		return vrstaFakture;
	}

	public void setVrstaFakture(boolean vrstaFakture) {
		this.vrstaFakture = vrstaFakture;
	}

	public long getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(long preduzece) {
		this.preduzece = preduzece;
	}

	public long getPoslovniPartner() {
		return poslovniPartner;
	}

	public void setPoslovniPartner(long poslovniPartner) {
		this.poslovniPartner = poslovniPartner;
	}

	public long getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public void setPoslovnaGodina(long poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}	
}
