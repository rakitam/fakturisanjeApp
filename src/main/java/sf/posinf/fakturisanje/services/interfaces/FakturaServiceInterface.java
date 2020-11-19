package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.StatusFakture;

import java.util.List;

//TODO: Za sad dodato vise metoda koje bi se potencijalno mogle koristiti; posle pobrisati one koje ne budu bili potrebne
public interface FakturaServiceInterface {

	List<Faktura> findAll();
	
	Faktura findOne(Long id);
    
    Faktura save(Faktura faktura);
    
    //Faktura moze biti obrisana samo u fazi formiranja!!!
    Boolean storniraj(Long id);
    
    Boolean update(Faktura faktura);
}
