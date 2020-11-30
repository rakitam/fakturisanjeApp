package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.model.StavkaFakture;

import java.util.List;

public interface StavkaFaktureServiceInterface {

	List<StavkaFakture> findAll();
	
    List<StavkaFakture> findByFaktura_id(Long id);
    
    StavkaFakture findOne(Long id);
    
    StavkaFakture save(StavkaFakture stavkaFakture);

    Boolean delete(Long id);

    void createSfFromSc(StavkaCenovnika stavka, int kolicina, int rabat, String email);

}
