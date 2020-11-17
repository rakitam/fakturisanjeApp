package sf.posinf.fakturisanje.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class Korisnik implements UserDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	//TODO: Otkomentarisati security u pom.xml
	//private Authority authority;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String imeIPrezime;
	
	private int brojTelefona;
	
	@OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
    private Set<Faktura> faktureKorisnika = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;
	
	private boolean obrisan;
	
	public Korisnik() {
		
	}	

	public Korisnik(long id, @NotNull String email, @NotNull String password, @NotNull String imeIPrezime,
			int brojTelefona, Set<Faktura> faktureKorisnika, Preduzece preduzece, boolean obrisan) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.imeIPrezime = imeIPrezime;
		this.brojTelefona = brojTelefona;
		this.faktureKorisnika = faktureKorisnika;
		this.preduzece = preduzece;
		this.obrisan = obrisan;
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

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authority = new ArrayList<>();
		//authority.add(this.authority);
		return authority;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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
}
