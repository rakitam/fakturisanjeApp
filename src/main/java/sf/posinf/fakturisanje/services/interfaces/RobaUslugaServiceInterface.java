package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import sf.posinf.fakturisanje.model.RobaUsluga;

public interface RobaUslugaServiceInterface {

	List<RobaUsluga> findAll(String naziv);
	
    RobaUsluga findOne(Long id);
    
    RobaUsluga save(RobaUsluga robaUsluga);
    
    List<RobaUsluga> findAllByGrupaRobe_id(Long id, String naziv);
	
}
