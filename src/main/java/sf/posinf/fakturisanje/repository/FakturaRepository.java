package sf.posinf.fakturisanje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.Faktura;

@Repository
public interface FakturaRepository extends JpaRepository<Faktura, Long> {
	
	List<Faktura> findAllByStatusFaktureAndKorisnik_Email(String statusFakture, String korisnikEmail);
	
	List<Faktura> findAllByStatusFaktureAndKorisnik_EmailAndPoslovnaGodina_Id(String statusFakture, String korisnikEmail, long poslovna_godina_id);
	
	List<Faktura> findAllByStatusFaktureAndPoslovnaGodina_Id(String statusFakture, long poslovnaGodina_id);

	List<Faktura> findAllByKorisnik_Email(String korisnikEmail);
	
	List<Faktura> findAllByPoslovnaGodina_Id(long poslovnaGodina_id);
	
	List<Faktura> findAllByPreduzece(String preduzece);
	
	List<Faktura> findByStatusFaktureAndPreduzece_IdAndPlaceno(String statusFakture, long id, boolean placeno);
	
	List<Faktura> findByStatusFaktureAndPreduzece_Id(String statusFakture,long id);
	
	//Proveriti da li sam dobro namapirala enum u bazi
	List<Faktura> findAllByStatusFakture(String statusFakture);
}

