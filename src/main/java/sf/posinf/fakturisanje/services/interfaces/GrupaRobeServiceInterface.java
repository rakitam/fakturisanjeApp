package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sf.posinf.fakturisanje.model.GrupaRobe;

public interface GrupaRobeServiceInterface {
	
	Page<GrupaRobe> findAll(boolean obrisano, String naziv, Pageable pageable);
	
    GrupaRobe findOne(Long id);
    
    List<GrupaRobe> findByPreduzece_id(Long id);
    
    GrupaRobe save(GrupaRobe grupaRobe);  
}
