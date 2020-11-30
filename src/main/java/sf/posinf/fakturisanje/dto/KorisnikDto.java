package sf.posinf.fakturisanje.dto;

public class KorisnikDto {

	private Long id;
	private String email;
	private String imePrezime;
	private int brojTelefona;
	//private Authority authority;
	
	public KorisnikDto() {

	}

	public KorisnikDto(Long id, String email, String imeIPrezime, int brojTelefona) {
		super();
		this.id = id;
		this.email = email;
		this.imePrezime = imeIPrezime;
		this.brojTelefona = brojTelefona;
		//this.authority = authority;
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

	public void setImePrezime(String imeIPrezime) {
		this.imePrezime = imeIPrezime;
	}

	public int getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(int brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	/*public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}*/
}
