package sf.posinf.fakturisanje.dto;

import com.sun.istack.NotNull;

public class PreduzeceDto {


	private long id;
    @NotNull
	private String naziv;
    @NotNull
	private String adresaPreduzeca;
    @NotNull
    private String PIB;
    @NotNull
	private String telefon;
    @NotNull
	private String email;
    @NotNull
	private String tekuciRacun;
    @NotNull
	private String logo;
    @NotNull
    private long mesto;
	
	public PreduzeceDto() {
		
	}
	
	public PreduzeceDto(String naziv, String adresaPreduzeca, String pIB, String telefon, String email,
			String tekuciRacun, String logo, long mesto) {
		super();
		this.naziv = naziv;
		this.adresaPreduzeca = adresaPreduzeca;
		PIB = pIB;
		this.telefon = telefon;
		this.email = email;
		this.tekuciRacun = tekuciRacun;
		this.logo = logo;
		this.mesto = mesto;
	}
	

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public PreduzeceDto setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
		return this;
	}

	public long getId() {
		return id;
	}

	public PreduzeceDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getNaziv() {
		return naziv;
	}

	public PreduzeceDto setNaziv(String naziv) {
		this.naziv = naziv;
		return this;
	}

	public String getAdresaPreduzeca() {
		return adresaPreduzeca;
	}

	public PreduzeceDto setAdresaPreduzeca(String adresaPreduzeca) {
		this.adresaPreduzeca = adresaPreduzeca;
		return this;
	}

	public String getPIB() {
		return PIB;
	}

	public PreduzeceDto setPIB(String pIB) {
		PIB = pIB;
		return this;
	}

	public String getTelefon() {
		return telefon;
	}

	public PreduzeceDto setTelefon(String telefon) {
		this.telefon = telefon;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public PreduzeceDto setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getLogo() {
		return logo;
	}

	public PreduzeceDto setLogo(String logo) {
		this.logo = logo;
		return this;
	}

	public long getMesto() {
		return mesto;
	}

	public PreduzeceDto setMesto(long mesto) {
		this.mesto = mesto;
		return this;
	}

}
