package sf.posinf.fakturisanje.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.Kupac;
import sf.posinf.fakturisanje.repository.KupacRepository;
import sf.posinf.fakturisanje.repository.PreduzeceRepository;
import sf.posinf.fakturisanje.services.interfaces.KupacServiceInterface;

public class KupacService implements KupacServiceInterface{

	@Autowired
	KupacRepository kupacRepository;
	
	@Autowired
	PreduzeceRepository preduzeceRepository;
	
	@Autowired 
	KupacServiceInterface kupacServiceInterface;
	
	//Filter?
	@Override
	public List<Kupac> findAll(String filter) {
		return kupacServiceInterface.findAll(filter);
	}

	@Override
	public List<Kupac> findAll() {
		return kupacRepository.findAll();
	}

	@Override
	public List<Kupac> findByPreduzece_id(Long id) {
		return kupacRepository.findByPreduzece_id(id);
	}

	// Sugavi optional	 
	public Optional<Kupac> findOne(Long id) {
		return kupacRepository.findById(id);
	}

	@Override
	public Kupac save(Kupac kupac) {
		return kupacServiceInterface.save(kupac);
	}

	@Override
	public Boolean delete(Long id) {
		return kupacServiceInterface.delete(id);
	}
}
