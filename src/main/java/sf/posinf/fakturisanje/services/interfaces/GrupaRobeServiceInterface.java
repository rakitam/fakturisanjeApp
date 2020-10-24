package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import sf.posinf.fakturisanje.model.GrupaRobe;

public interface GrupaRobeServiceInterface {
	
	List<GrupaRobe> findAll(String naziv);
	
    GrupaRobe findOne(Long id);
    
    List<GrupaRobe> findByPreduzece_id(Long id);
    
    GrupaRobe save(GrupaRobe grupaRobe);
    
    Boolean delete(Long id);
	
	
}
