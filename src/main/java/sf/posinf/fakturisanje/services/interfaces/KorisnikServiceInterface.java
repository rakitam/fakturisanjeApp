package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.Korisnik;

import java.util.List;

public interface KorisnikServiceInterface {

	List<Korisnik> findAll();

	Korisnik save(Korisnik korisnik);
}
