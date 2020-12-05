package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.model.StatusFakture;

import java.util.List;

public interface FakturaServiceInterface {

	Page<Faktura> findAll(String statusFakture, Pageable pageable);

    Page<Faktura> findAllByKorisnik(long korisnik_id, String statusFakture, Pageable pageable);

    Faktura findOne(Long id);
    
    Faktura save(Faktura faktura);
    
    //Faktura moze biti obrisana samo u fazi formiranja!!!
    Boolean storniraj(Faktura faktura);

    Faktura getActiveFakturaForKorisnik(Korisnik korisnik);

    List<Faktura> findAllByPreduzece_IdAndPoslovnaGodina_Godina(long preduzeceId, int godina);
    
    Boolean update(Faktura faktura);

    void racunaj(Long id);
}
