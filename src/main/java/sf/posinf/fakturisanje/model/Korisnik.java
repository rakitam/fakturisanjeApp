package sf.posinf.fakturisanje.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Korisnik implements UserDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String imePrezime;
	
	private String brojTelefona;
	
	@OneToMany(mappedBy = "korisnik")
    private Set<Faktura> faktureKorisnika = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;

	@ManyToOne
	@JoinColumn(name = "uloga_id")
	private Uloga uloga;

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Uloga> uloge = new ArrayList<>();
		uloge.add(this.uloga);
		return uloge;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Faktura> getFaktureKorisnika() {
		return faktureKorisnika;
	}

	public void setFaktureKorisnika(Set<Faktura> faktureKorisnika) {
		this.faktureKorisnika = faktureKorisnika;
	}

	public Preduzece getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Preduzece preduzece) {
		this.preduzece = preduzece;
	}
}
