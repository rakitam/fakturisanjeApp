package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.StatusFakture;

//TODO: Za sad dodato vise metoda koje bi se potencijalno mogle koristiti; posle pobrisati one koje ne budu bili potrebne
public interface FakturaServiceInterface {

	List<Faktura> findAll();
	
	Faktura findOne(Long id);
    
    List<Faktura> findAllByPreduzece(String preduzece);
    
    List<Faktura> findAllByStatusFaktureAndKorisnik_EmailAndPoslovnaGodina(String statusFakture, String korisnikEmail, long poslovnaGodina);
    
    List<Faktura> findAllByPoslovnaGodina(long poslovnaGodina);
    
    List<Faktura> findAllByStatusFakture(String statusFakture);
    
    Faktura save(Faktura faktura);
    
    //Faktura moze biti obrisana samo u fazi formiranja!!!
    Boolean storniraj(Long id);
    
    Boolean update(Faktura faktura);

	Page<Faktura> findAllByPoslovnaGodinaAndPreduzeceId(int godina, String preduzece, Pageable pageable);

	List<Faktura> findAllByKorisnik_Id(long korisnikId);
}
