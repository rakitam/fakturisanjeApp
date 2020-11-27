package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Mesto;
import sf.posinf.fakturisanje.model.StatusFakture;
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
		Faktura faktura = stavkaFakture.getFaktura();
		if (faktura.getStatusFakture() != StatusFakture.PORUDZBENICA) {
			return null;
		}
		stavkaFaktureRepository.save(stavkaFakture);
        fakturaServiceInterface.update(stavkaFakture.getFaktura());
        return stavkaFakture;
	}

	//TODO: Testirati metodu
	@Override
	public Boolean delete(Long id) {
		StavkaFakture sf = stavkaFaktureRepository.getOne(id);
		Faktura faktura = sf.getFaktura();
		if (sf == null) {
			return false;
		}
		else if (faktura.getStatusFakture() != StatusFakture.PORUDZBENICA) {
			return false;
		}
		sf.setObrisana(true);
		stavkaFaktureRepository.saveAndFlush(sf);
		//TODO: Treba update-ovati i fakturu, jer se na njoj stavka vise ne nalazi?
		fakturaServiceInterface.update(sf.getFaktura());
		return true;
	}
}
