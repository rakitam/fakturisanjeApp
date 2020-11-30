package sf.posinf.fakturisanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.StatusFakture;

import java.util.List;

@Repository
public interface FakturaRepository extends JpaRepository<Faktura, Long> {

	List<Faktura> findAllByStatusFaktureAndKorisnik_EmailAndPoslovnaGodina_Id(StatusFakture statusFakture, String korisnikEmail, long poslovna_godina_id);
	
	List<Faktura> findAllByPoslovnaGodina_Id(long poslovnaGodina_id);
	
	List<Faktura> findAllByPreduzece(String preduzece);
	
	//Proveriti da li sam dobro namapirala enum u bazi
	Page<Faktura> findAllByStatusFakture(StatusFakture statusFakture, Pageable pageable);
	
	Page<Faktura> findAllByPoslovnaGodina_IdAndPreduzece_Id(long poslovnaGodina, long preduzece, Pageable pageable);
	
	Page<Faktura> findAllByPreduzece_Id(long naziv, Pageable pageable);

	List<Faktura> findAllByKorisnik_Id(long korisnikId);

	Faktura findByKorisnik_IdAndStatusFakture(long korisnikId, StatusFakture sf);

	List<Faktura> findAllByPreduzece_IdAndPoslovnaGodina_Id(long preduzece, long poslovnaGodina);

	List<Faktura> findAllByPreduzece_Id(long preduzece);
}

