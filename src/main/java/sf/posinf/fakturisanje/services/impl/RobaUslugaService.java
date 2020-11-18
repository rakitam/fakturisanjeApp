package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sf.posinf.fakturisanje.model.RobaUsluga;
import sf.posinf.fakturisanje.repository.RobaUslugaRepository;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;

@Service
public class RobaUslugaService implements RobaUslugaServiceInterface {

	@Autowired
	RobaUslugaRepository robaUslugaRepository;

	@Override
	public Page<RobaUsluga> findAll(String naziv, Pageable pageable) {
		return robaUslugaRepository.findAllByNazivRobeUslugeIgnoreCaseContains(naziv, pageable);
	}

	@Override
	public RobaUsluga findOne(Long id) {
		return robaUslugaRepository.findById(id).orElse(null);
	}

	@Override
	public Page<RobaUsluga> findAllByGrupaRobe_id(Long grupaRobeId, boolean obrisana, String naziv, Pageable pageable) {
		return robaUslugaRepository.findAllByGrupaRobe_idAndObrisanoAndNazivRobeUslugeIgnoreCaseContains(grupaRobeId, false, naziv,
				pageable);
	}

	@Override
	public RobaUsluga save(RobaUsluga robaUsluga) {
		robaUslugaRepository.save(robaUsluga);
		return robaUsluga;
	}

	@Override
	public Boolean delete(Long id) {
		robaUslugaRepository.deleteById(id);
		return true;
	}
}
