package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sf.posinf.fakturisanje.model.RobaUsluga;

public interface RobaUslugaServiceInterface {

	Page<RobaUsluga> findAll(String naziv, Pageable pageable);
	
    RobaUsluga findOne(Long id);
    
    RobaUsluga save(RobaUsluga robaUsluga);
    
    Page<RobaUsluga> findAllByGrupaRobe_idAndNaziv(Long grupaRobeid, String naziv, Pageable pageable);
	
}
