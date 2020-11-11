package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;
import java.util.Optional;

import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Kupac;

public interface KupacServiceInterface {

    List<Kupac> findAll(String filter);
    
    List<Kupac> findAll();
    
    List<Kupac> findByPreduzece_id(Long id);
    
    Optional<Kupac> findOne(Long id);
    
    Kupac save(Kupac kupac);
    
    Boolean delete(Long id);
}
