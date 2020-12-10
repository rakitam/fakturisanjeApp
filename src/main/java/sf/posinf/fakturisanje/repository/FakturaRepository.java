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

	List<Faktura> findAllByPoslovnaGodina_Id(long poslovnaGodinaId);

	Page<Faktura> findAllByStatusFakture(StatusFakture statusFakture, Pageable pageable);
	
	Page<Faktura> findAllByKorisnik_IdAndStatusFakture(long korisnikId, StatusFakture sf, Pageable pageable);

	Page<Faktura> findAllByKorisnik_Id(long korisnikId, Pageable pageable);

	Faktura findByKorisnik_IdAndStatusFakture(long korisnikId, StatusFakture sf);

	List<Faktura> findAllByPreduzece_IdAndPoslovnaGodina_Id(long preduzece, long poslovnaGodina);

	List<Faktura> findAllByPreduzece_IdAndPoslovnaGodina_Godina(long preduzece, int poslovnaGodina);

	List<Faktura> findAllByPreduzece_Id(long preduzece);
}

