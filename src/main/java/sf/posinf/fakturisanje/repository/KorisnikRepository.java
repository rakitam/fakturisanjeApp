package sf.posinf.fakturisanje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sf.posinf.fakturisanje.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

	Korisnik findByEmail(String email);
	
	List<Korisnik> findAllByEmail(String email);
	
	Korisnik findByImeIPrezime(String imeIPrezime);
	
	Korisnik findByBrojTelefona(int brojTelefona);
	
	List<Korisnik> findByObrisanFalse();
	
	List<Korisnik> findByObrisanTrue();
		
	List<Korisnik> findAll();
}
