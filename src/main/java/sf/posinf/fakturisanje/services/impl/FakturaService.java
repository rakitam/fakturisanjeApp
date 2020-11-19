package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.StatusFakture;
import sf.posinf.fakturisanje.repository.FakturaRepository;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;

import java.util.List;

@Service
public class FakturaService implements FakturaServiceInterface {

	@Autowired
	FakturaRepository fakturaRepository;

	@Override
	public List<Faktura> findAll() {
		return fakturaRepository.findAll();
	}

	@Override
	public Faktura findOne(Long id) {
		return fakturaRepository.getOne(id);
	}

	@Override
	public Faktura save(Faktura faktura) {
		fakturaRepository.save(faktura);
		return faktura;
	}

	// Faktura moze samo da se stornira
	@Override
	public Boolean storniraj(Long id) {
		Faktura fakturaDB = fakturaRepository.getOne(id);
		if(fakturaDB.getStatusFakture() != StatusFakture.STORNIRANA) {
			fakturaDB.setStatusFakture(StatusFakture.STORNIRANA);
			return true;
		}		
		return false;
	}

	// Faktura ne moze da se brise sem ako nije u fazi formiranja!
	// Ukoliko menjamo stavke fakture, pravi se nova faktura, a prethodna se
	// stornira
	@Override
	public Boolean update(Faktura faktura) {
		Faktura fakturaDB = fakturaRepository.getOne(faktura.getId());
		if (fakturaDB.getStatusFakture() == StatusFakture.FORMIRANA
				|| faktura.getStatusFakture() != StatusFakture.FORMIRANA) {
			fakturaDB.setStatusFakture(faktura.getStatusFakture());
			save(fakturaDB);
			return true;
		} else {
			return false;
		}
	}
}
