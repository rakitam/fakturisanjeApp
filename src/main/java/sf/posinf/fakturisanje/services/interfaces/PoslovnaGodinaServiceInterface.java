package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import sf.posinf.fakturisanje.model.PoslovnaGodina;

public interface PoslovnaGodinaServiceInterface {

	List<PoslovnaGodina> findAll();
	
    PoslovnaGodina findOne(Long id);
    
    PoslovnaGodina save(PoslovnaGodina poslovnaGodina);
    
    //Poslovna godina ne bi trebalo da se brise
    //Boolean delete(Long id);

    PoslovnaGodina findByZakljucanaIsFalse();
}
