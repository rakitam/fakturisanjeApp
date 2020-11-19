package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.PoslovnaGodina;

import java.util.List;

public interface PoslovnaGodinaServiceInterface {

	List<PoslovnaGodina> findAll();
	
    PoslovnaGodina findOne(Long id);
    
    PoslovnaGodina save(PoslovnaGodina poslovnaGodina);

    PoslovnaGodina findByZakljucanaIsFalse();
}
