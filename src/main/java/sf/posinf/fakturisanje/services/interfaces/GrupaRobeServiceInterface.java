package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import sf.posinf.fakturisanje.model.GrupaRobe;

public interface GrupaRobeServiceInterface {
	
	Page<GrupaRobe> findAll(String naziv, int brojStranice, int brojPrikazanih);
	
    GrupaRobe findOne(Long id);
    
    List<GrupaRobe> findByPreduzece_id(Long id);
    
    GrupaRobe save(GrupaRobe grupaRobe);
    
    void update(GrupaRobe grupaRobe);    
}
