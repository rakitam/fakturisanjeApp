package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.Korisnik;

import java.util.List;

public interface KorisnikServiceInterface {

	List<Korisnik> findAll();

	Korisnik findByEmail(String email);

	Korisnik save(Korisnik korisnik);
}
