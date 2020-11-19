package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.GrupaRobe;
import sf.posinf.fakturisanje.repository.GrupaRobeRepository;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;

import java.util.List;

@Service
public class GrupaRobeService implements GrupaRobeServiceInterface {
	
	@Autowired
	GrupaRobeRepository grupaRobeRepository;
	
	@Override
	public Page<GrupaRobe> findAll(boolean obrisano, String naziv, Pageable pageable) {
		return grupaRobeRepository.findAllByObrisanoAndNazivGrupeIgnoreCaseContains(obrisano, naziv, pageable);
	}

	@Override
	public GrupaRobe findOne(Long id) {
		return grupaRobeRepository.getOne(id);
	}

	@Override
	public List<GrupaRobe> findByPreduzece_id(Long id) {
		return grupaRobeRepository.findAllByPreduzece_id(id);
	}

	@Override
	public GrupaRobe save(GrupaRobe grupaRobe) {
		return grupaRobeRepository.save(grupaRobe);
	}
}
