package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.PoslovniPartner;

public interface PoslovniPartnerServiceInterface {

    List<PoslovniPartner> findAll(String filter);
    
    List<PoslovniPartner> findAll();
    
    List<PoslovniPartner> findByPreduzece_id(Long id);
    
    PoslovniPartner findOne(Long id);
    
    PoslovniPartner save(PoslovniPartner poslovniPartner);
    
    Boolean delete(Long id);
    
    PoslovniPartner findPartner(Cenovnik cenovnik);
}
