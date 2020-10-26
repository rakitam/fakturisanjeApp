package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sf.posinf.fakturisanje.model.PoslovnaGodina;
import sf.posinf.fakturisanje.repository.PoslovnaGodinaRepository;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;

public class PoslovnaGodinaService implements PoslovnaGodinaServiceInterface {
	
	@Autowired
	PoslovnaGodinaRepository poslovnaGodinaRepository;

	@Override
	public List<PoslovnaGodina> findAll() {
		return poslovnaGodinaRepository.findAll();
	}

	@Override
	public PoslovnaGodina findOne(Long id) {
		return poslovnaGodinaRepository.findById(id).orElse(null);
	}

	@Override
	public PoslovnaGodina save(PoslovnaGodina poslovnaGodina) {
		poslovnaGodinaRepository.save(poslovnaGodina);
        return poslovnaGodina;
	}

	//TODO: Razmisliti da li se sme brisati poslovna godina
	@Override
	public Boolean delete(Long id) {
		return null;
	}

	@Override
	public List<PoslovnaGodina> FindByZakljucanaIsFalse() {
		return poslovnaGodinaRepository.findByZakljucanaIsFalse();
	}

}
