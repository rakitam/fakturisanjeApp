package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.StavkaFakture;

import java.util.List;

public interface StavkaFaktureServiceInterface {

	List<StavkaFakture> findAll();
	
    List<StavkaFakture> findByFaktura_id(Long id);
    
    StavkaFakture findOne(Long id);
    
    StavkaFakture save(StavkaFakture stavkaFakture);
}
