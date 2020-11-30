package sf.posinf.fakturisanje.dto;

import sf.posinf.fakturisanje.model.Korisnik;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class FakturaDto {

	private long id;
	private long brojFakture;
	private Date datumFakture;
	private Date datumValute;
	private Date datumStorniranja;
	private double iznosBezRabata;
	private double rabat;
	private double osnovica;
	private double ukupanPdv;
	private double iznosZaPlacanje;
	private String statusFakture;
	@NotNull
	private long preduzece;
	private PoslovnaGodinaDto poslovnaGodina;
	private String korisnik;
	
	public FakturaDto() {
		
	}

	public FakturaDto(long id, long brojFakture, Date datumFakture, Date datumValute, Date datumStorniranja, double iznosBezRabata, double rabat, double osnovica, double ukupanPdv, double iznosZaPlacanje, String statusFakture, @NotNull long preduzece, PoslovnaGodinaDto poslovnaGodina, String korisnik) {
		this.id = id;
		this.brojFakture = brojFakture;
		this.datumFakture = datumFakture;
		this.datumValute = datumValute;
		this.datumStorniranja = datumStorniranja;
		this.iznosBezRabata = iznosBezRabata;
		this.rabat = rabat;
		this.osnovica = osnovica;
		this.ukupanPdv = ukupanPdv;
		this.iznosZaPlacanje = iznosZaPlacanje;
		this.statusFakture = statusFakture;
		this.preduzece = preduzece;
		this.poslovnaGodina = poslovnaGodina;
		this.korisnik = korisnik;
	}

	public long getId() {
		return id;
	}

	public long getBrojFakture() {
		return brojFakture;
	}

	public Date getDatumFakture() {
		return datumFakture;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public Date getDatumStorniranja() {
		return datumStorniranja;
	}

	public double getIznosBezRabata() {
		return iznosBezRabata;
	}

	public double getRabat() {
		return rabat;
	}

	public double getOsnovica() {
		return osnovica;
	}

	public double getUkupanPdv() {
		return ukupanPdv;
	}

	public double getIznosZaPlacanje() {
		return iznosZaPlacanje;
	}

	public String getStatusFakture() {
		return statusFakture;
	}

	public long getPreduzece() {
		return preduzece;
	}

	public PoslovnaGodinaDto getPoslovnaGodina() {
		return poslovnaGodina;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setBrojFakture(long brojFakture) {
		this.brojFakture = brojFakture;
	}

	public void setDatumFakture(Date datumFakture) {
		this.datumFakture = datumFakture;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public void setDatumStorniranja(Date datumStorniranja) {
		this.datumStorniranja = datumStorniranja;
	}

	public void setIznosBezRabata(double iznosBezRabata) {
		this.iznosBezRabata = iznosBezRabata;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public void setOsnovica(double osnovica) {
		this.osnovica = osnovica;
	}

	public void setUkupanPdv(double ukupanPdv) {
		this.ukupanPdv = ukupanPdv;
	}

	public void setIznosZaPlacanje(double iznosZaPlacanje) {
		this.iznosZaPlacanje = iznosZaPlacanje;
	}

	public void setStatusFakture(String statusFakture) {
		this.statusFakture = statusFakture;
	}

	public void setPreduzece(long preduzece) {
		this.preduzece = preduzece;
	}

	public void setPoslovnaGodina(PoslovnaGodinaDto poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}
}
