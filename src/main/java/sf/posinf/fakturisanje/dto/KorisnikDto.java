package sf.posinf.fakturisanje.dto;

import java.util.HashSet;
import java.util.Set;

public class KorisnikDto {

	private Long id;
	private String email;
	private String imeIPrezime;
	private int brojTelefona;
	//private Authority authority;
	
	public KorisnikDto() {

	}

	public KorisnikDto(Long id, String email, String imeIPrezime, int brojTelefona) {
		super();
		this.id = id;
		this.email = email;
		this.imeIPrezime = imeIPrezime;
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

	public String getImeIPrezime() {
		return imeIPrezime;
	}

	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
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
