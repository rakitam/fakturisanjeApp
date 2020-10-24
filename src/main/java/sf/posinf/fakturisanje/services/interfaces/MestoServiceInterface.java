package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;

import sf.posinf.fakturisanje.model.Mesto;

public interface MestoServiceInterface {

    Page<Mesto> findAll(String naziv, int brojStranice, int brojPrikazanih);
    
    Mesto findOne(Long id);
    
    Mesto save(Mesto mesto);
    
    Boolean delete(Long id);
}
