package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sf.posinf.fakturisanje.model.PoslovnaGodina;
import sf.posinf.fakturisanje.repository.PoslovnaGodinaRepository;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;

@Service
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

	@Override
	public PoslovnaGodina findByZakljucanaIsFalse() {
		return poslovnaGodinaRepository.findByZakljucanaIsFalse();
	}

}
