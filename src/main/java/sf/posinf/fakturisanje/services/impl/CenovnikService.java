package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.repository.CenovnikRepository;
import sf.posinf.fakturisanje.repository.StavkaCenovnikaRepository;
import sf.posinf.fakturisanje.services.interfaces.CenovnikServiceInterface;

@Service
public class CenovnikService implements CenovnikServiceInterface {

	@Autowired
	CenovnikRepository cenovnikRepository;

	@Autowired
	StavkaCenovnikaRepository stavkaCenovnikaRepository;
	
	@Override
	public Page<StavkaCenovnika> findAllByCenovnikId(long id, String nazivRobeUsluge, Pageable pageable) {
		return stavkaCenovnikaRepository.findAllByObrisanoAndCenovnik_IdAndRobaUsluga_NazivRobeUslugeIgnoreCaseContains(false, id, nazivRobeUsluge, pageable);
	}
	
	@Override
	public Cenovnik findOne(Long id) {
		return cenovnikRepository.findByObrisanoAndId(false, id);
	}

	@Override
	public Cenovnik save(Cenovnik cenovnik) {
		return cenovnikRepository.save(cenovnik);
	}

	@Override
	public Boolean delete(Long id) {
		Cenovnik cenovnik = cenovnikRepository.getOne(id);
		if (cenovnik == null) {
			return false;
		}
		cenovnik.setObrisano(true);
		cenovnikRepository.saveAndFlush(cenovnik);
		return true;
	}

	@Override
	public Page<Cenovnik> findAll(Pageable pageable) {
		return cenovnikRepository.findAll(pageable);
	}
}
