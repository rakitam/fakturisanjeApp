package sf.posinf.fakturisanje.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Korisnik {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotNull
	@Min(0)
    @Max(1)
	private boolean tipKorisnika; //true = admin, false = kupac
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String imeIPrezime;
	
	private int brojTelefona;	
	
	
}
