package sf.posinf.services.interfaces;

import java.util.List;

import sf.posinf.model.PoslovnaGodina;

public interface PoslovnaGodinaServiceInterface {

	List<PoslovnaGodina> findAll();
	
    PoslovnaGodina findOne(Long id);
    
    PoslovnaGodina save(PoslovnaGodina poslovnaGodina);
    
    Boolean delete(Long id);

    PoslovnaGodina FindByZakljucanaIsFalse();
}
