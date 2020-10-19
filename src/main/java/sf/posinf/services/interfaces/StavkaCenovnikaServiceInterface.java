package sf.posinf.services.interfaces;
import java.util.List;

import sf.posinf.model.StavkaCenovnika;
public interface StavkaCenovnikaServiceInterface {

    List<StavkaCenovnika> findAll();
    StavkaCenovnika findOne(Long id);
    StavkaCenovnika save(StavkaCenovnika stavkaCenovnika);
    List<StavkaCenovnika> findAllByRoba_usluga_id(Long id);
    Boolean delete(Long id);
}
