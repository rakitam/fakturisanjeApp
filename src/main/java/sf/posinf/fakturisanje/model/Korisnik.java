package sf.posinf.fakturisanje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@Entity
public class Korisnik {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String imeIPrezime;
	
	private String brojTelefona;
	
	@OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
    private Set<Faktura> faktureKorisnika = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;
	
	private boolean obrisan;
	
	public Korisnik() {
		
	}	

	public Korisnik(long id, @NotNull String email, @NotNull String password, @NotNull String imeIPrezime,
			String brojTelefona, Set<Faktura> faktureKorisnika, Preduzece preduzece, boolean obrisan) {
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

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getPassword() {
		return password;
	}
}
