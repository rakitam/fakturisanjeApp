package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.Korisnik;

import java.util.List;

public interface KorisnikServiceInterface {

	Korisnik findById(Long id);

	Korisnik findByEmail(String email);
	
	Korisnik findByImeIPrezime(String imeIPrezime);
	
	Korisnik findByBrojTelefona(int brojTelefona);

	List<Korisnik> findAll();

	List<Korisnik> findByObrisanTrue();

	List<Korisnik> findByObrisanFalse();

	Korisnik save(Korisnik korisnik);
	
	void update(Korisnik korisnik);
	
	Boolean delete(Long id);
}
