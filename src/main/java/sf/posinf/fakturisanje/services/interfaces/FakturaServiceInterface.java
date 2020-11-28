package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.model.StatusFakture;

import java.util.List;

public interface FakturaServiceInterface {

	List<Faktura> findAll();
	
	Faktura findOne(Long id);
    
    Faktura save(Faktura faktura);
    
    //Faktura moze biti obrisana samo u fazi formiranja!!!
    Boolean storniraj(Faktura faktura);

    Faktura getActiveFakturaForKorisnik(Korisnik korisnik);
    
    Boolean update(Faktura faktura);

    void racunaj(Long id);
}
