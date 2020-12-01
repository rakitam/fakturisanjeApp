package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.StatusFakture;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.repository.CenovnikRepository;
import sf.posinf.fakturisanje.repository.StavkaCenovnikaRepository;
import sf.posinf.fakturisanje.services.interfaces.CenovnikServiceInterface;

import java.util.Date;

@Service
public class CenovnikService implements CenovnikServiceInterface {

	@Autowired
	CenovnikRepository cenovnikRepository;

	@Autowired
	StavkaCenovnikaRepository stavkaCenovnikaRepository;
	
	@Override
	public Page<StavkaCenovnika> findAllByCenovnikId(long id, String nazivRobeUsluge, Pageable pageable) {
		return stavkaCenovnikaRepository.findAllByCenovnik_IdAndRobaUsluga_NazivRobeUslugeIgnoreCaseContains(id, nazivRobeUsluge, pageable);
	}
	
	@Override
	public Cenovnik findOne(Long id) {
		return cenovnikRepository.getOne(id);
	}

	@Override
	public Cenovnik save(Cenovnik cenovnik) {
		return cenovnikRepository.save(cenovnik);
	}

	@Override
	public Boolean proglasiNeaktivnim(Cenovnik cenovnik) {
		if(cenovnik.isAktivan() != false) {
			cenovnik.setAktivan(false);
			save(cenovnik);
			return true;
		}
		return false;
	}

	@Override
	public Page<Cenovnik> findAll(Pageable pageable) {
		return cenovnikRepository.findAll(pageable);
	}
}
