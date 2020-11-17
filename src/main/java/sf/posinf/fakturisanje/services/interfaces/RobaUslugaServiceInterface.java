package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;

import sf.posinf.fakturisanje.model.RobaUsluga;

public interface RobaUslugaServiceInterface {

	Page<RobaUsluga> findAll(String naziv, int brojStanice, int brojPrikazanih);
	
    RobaUsluga findOne(Long id);
    
    Boolean delete(Long id);
    
    RobaUsluga save(RobaUsluga robaUsluga);
    
    void update(RobaUsluga robaUsluga);
    
    Page<RobaUsluga> findAllByGrupaRobe_id(Long id, String naziv, int brojStanice, int brojPrikazanih);
	
}
