package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import sf.posinf.fakturisanje.model.GrupaRobe;
import sf.posinf.fakturisanje.model.RobaUsluga;

public interface RobaUslugaServiceInterface {

	Page<RobaUsluga> findAll(String naziv, Pageable pageable);
	
    RobaUsluga findOne(Long id);
    
    Boolean delete(Long id);
    
    RobaUsluga save(RobaUsluga robaUsluga);
    
    Page<RobaUsluga> findAllByGrupaRobe_id(Long grupaRobeid, boolean obrisana, String naziv, Pageable pageable);
	
}
