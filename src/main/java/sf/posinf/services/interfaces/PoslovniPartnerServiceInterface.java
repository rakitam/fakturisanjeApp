package sf.posinf.services.interfaces;

import java.util.List;

import sf.posinf.model.Cenovnik;
import sf.posinf.model.PoslovniPartner;

public interface PoslovniPartnerServiceInterface {

    List<PoslovniPartner> findAll(String filter);
    List<PoslovniPartner> findAll();
    List<PoslovniPartner> findByPreduzece_id(Long id);
    PoslovniPartner findOne(Long id);
    PoslovniPartner save(PoslovniPartner poslovniPartner);
    Boolean delete(Long id);
    PoslovniPartner findPartner(Cenovnik cenovnik);
}
