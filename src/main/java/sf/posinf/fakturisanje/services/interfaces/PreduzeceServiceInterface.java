package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Preduzece;

import java.util.List;

public interface PreduzeceServiceInterface {

    List<Preduzece> findAll();
    
    List<Faktura> findAllByPreduzeceAndPoslovnaGodina(long preduzece, long poslovnaGodina);

    List<Faktura> findAllByPreduzeceAndStatusFaktureAndPlaceno(long id, String statusFakture, boolean placeno);
    
    Preduzece findOne(Long id);
    
    Preduzece save(Preduzece preduzece);
}
