package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.StavkaFakture;
import sf.posinf.fakturisanje.repository.StavkaFaktureRepository;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

import java.util.List;

@Service
public class StavkaFaktureService implements StavkaFaktureServiceInterface {
	
	@Autowired
	StavkaFaktureRepository stavkaFaktureRepository;
	
	@Autowired
	FakturaServiceInterface fakturaServiceInterface;

	@Override
	public List<StavkaFakture> findAll() {
		return stavkaFaktureRepository.findAll();
	}

	@Override
	public List<StavkaFakture> findByFaktura_id(Long id) {
		return stavkaFaktureRepository.findByFaktura_id(id);
	}

	@Override
	public StavkaFakture findOne(Long id) {
		return stavkaFaktureRepository.getOne(id);
	}

	@Override
	public StavkaFakture save(StavkaFakture stavkaFakture) {
		stavkaFaktureRepository.save(stavkaFakture);
        fakturaServiceInterface.update(stavkaFakture.getFaktura());
        return stavkaFakture;
	}

	//TODO: Razmisliti u kom momentu sme da se brise stavka fakture
	@Override
	public Boolean delete(Long id) {
		return null;
	}
}
