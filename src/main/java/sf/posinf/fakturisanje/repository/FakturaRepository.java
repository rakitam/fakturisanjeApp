package sf.posinf.fakturisanje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.Faktura;

@Repository
public interface FakturaRepository extends JpaRepository<Faktura, Long> {

	List<Faktura> findAllByVrstaFakture(boolean vrstaFakture);
	
	List<Faktura> findAllByVrstaFaktureAndPoslovniPartner_NazivPartnera(boolean vrstaFakture, String nazivPartnera);
	
	List<Faktura> findAllByVrstaFaktureAndPoslovniPartner_NazivPartneraAndPoslovnaGodina_Id(boolean vrstaFakture, String nazivPartnera, long poslovna_godina_id);
	
	List<Faktura> findAllByVrstaFaktureAndPoslovnaGodina_Id(boolean vrstaFakture, long poslovnaGodina_id);

	List<Faktura> findAllByPoslovniPartner_NazivPartnera(String nazivPartnera);
	
	List<Faktura> findAllByPoslovnaGodina_Id(long poslovnaGodina_id);
	
	//Proveriti da li sam dobro namapirala enum u bazi
	List<Faktura> findAllByStatusFakture(String statusFakture);
}

