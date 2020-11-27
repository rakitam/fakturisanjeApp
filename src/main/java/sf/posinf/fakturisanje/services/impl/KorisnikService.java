
package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.repository.KorisnikRepository;
import sf.posinf.fakturisanje.services.interfaces.KorisnikServiceInterface;

import java.util.List;

@Service
public class KorisnikService implements KorisnikServiceInterface {

	@Autowired
	private KorisnikRepository korisnikRepository;
	


	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public Korisnik findByEmail(String email) {
		return korisnikRepository.findByEmail(email);
	}


	@Override
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}
}
