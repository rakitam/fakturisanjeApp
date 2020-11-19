package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sf.posinf.fakturisanje.model.Mesto;

public interface MestoServiceInterface {
    
    Mesto findOne(Long id);
    
    Mesto save(Mesto mesto);
    
    Boolean delete(Long id);

	Page<Mesto> findAll(String naziv, boolean obrisano, Pageable pageable);
}
