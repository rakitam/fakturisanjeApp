package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Preduzece;

public interface PreduzeceServiceInterface {

    List<Preduzece> findAll();
    
    List<Faktura> findAllByPreduzeceAndStatusFakture(long id, String statusFakture);
    
    List<Faktura> findAllByPreduzeceAndStatusFaktureAndPlaceno(long id, String statusFakture, boolean placeno);
    
    Preduzece findOne(Long id);
    
    Preduzece save(Preduzece preduzece);
    
    Boolean delete(Long id);
    
    void update(Preduzece preduzece);
}
