
package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.repository.KorisnikRepository;
import sf.posinf.fakturisanje.services.interfaces.KorisnikServiceInterface;

@Service
public class KorisnikService implements KorisnikServiceInterface {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	public Korisnik findById(Long id) {
		return korisnikRepository.getOne(id);		
	}

	@Override
	public Korisnik findByEmail(String email) {
		return korisnikRepository.findByEmail(email);
	}

	@Override
	public Korisnik findByImeIPrezime(String imeIPrezime) {
		return korisnikRepository.findByImeIPrezime(imeIPrezime);
	}

	@Override
	public Korisnik findByBrojTelefona(int brojTelefona) {
		return korisnikRepository.findByBrojTelefona(brojTelefona);
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public List<Korisnik> findByObrisanTrue() {
		return korisnikRepository.findByObrisanTrue();
	}

	@Override
	public List<Korisnik> findByObrisanFalse() {
		return korisnikRepository.findByObrisanFalse();
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

	@Override
	public void update(Korisnik korisnik) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Boolean delete(Long id) {
		Korisnik korisnik = korisnikRepository.findById(id).orElse(null);
		korisnik.setObrisan(true);        
		korisnikRepository.saveAndFlush(korisnik);
        return true;
	}
}
