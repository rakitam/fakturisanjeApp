package sf.posinf.services.interfaces;

import java.util.List;

import sf.posinf.model.StavkaFakture;

public interface StavkaFaktureServiceInterface {

	List<StavkaFakture> findAll();
	
    List<StavkaFakture> findByFaktura_id(Long id);
    
    StavkaFakture findOne(Long id);
    
    StavkaFakture save(StavkaFakture stavkaFakture);
    
    Boolean delete(Long id);
}
