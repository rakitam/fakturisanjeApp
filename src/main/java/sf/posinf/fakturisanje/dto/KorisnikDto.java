package sf.posinf.fakturisanje.dto;

public class KorisnikDto {

	private Long id;
	private String email;
	private String imePrezime;
	private String brojTelefona;
	private String adresaKorisnika;
	private String tekuciRacun;

	public KorisnikDto() {

	}

	public KorisnikDto(Long id, String email, String imePrezime, String brojTelefona, String adresaKorisnika, String tekuciRacun) {
		this.id = id;
		this.email = email;
		this.imePrezime = imePrezime;
		this.brojTelefona = brojTelefona;
		this.adresaKorisnika = adresaKorisnika;
		this.tekuciRacun = tekuciRacun;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getAdresaKorisnika() {
		return adresaKorisnika;
	}

	public void setAdresaKorisnika(String adresaKorisnika) {
		this.adresaKorisnika = adresaKorisnika;
	}

	public String getTekuciRacun() {
		return tekuciRacun;
	}

	public void setTekuciRacun(String tekuciRacun) {
		this.tekuciRacun = tekuciRacun;
	}
}
