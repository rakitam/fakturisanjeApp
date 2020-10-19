package sf.posinf.services.interfaces;

import org.springframework.data.domain.Page;

import sf.posinf.model.Cenovnik;
import sf.posinf.model.StavkaCenovnika;

public interface CenovnikServiceInterface {

    Page<Cenovnik> findAll(int brojStranice, int brojPrikazanih);
    Page<StavkaCenovnika> findAllByCenovnikId(long id, String nazivRobeUsluge,int brojStranice, int brojPrikazanih);
    Cenovnik findOne(Long id);
    Cenovnik save(Cenovnik cenovnik);
    Boolean delete(Long id);
}
