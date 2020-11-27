package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.*;
import sf.posinf.fakturisanje.repository.StavkaFaktureRepository;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.KorisnikServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PDV_ServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

import java.util.List;

@Service
public class StavkaFaktureService implements StavkaFaktureServiceInterface {
	
	@Autowired
	StavkaFaktureRepository stavkaFaktureRepository;

	@Autowired
	KorisnikServiceInterface korisnikServiceInterface;
	
	@Autowired
	FakturaServiceInterface fakturaServiceInterface;

	@Autowired
	PDV_ServiceInterface pdv_serviceInterface;

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

	//Popraviti u zavisnosti od toga da li se cuva ili menja
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

	@Override
	public Boolean delete(Long id) {
		StavkaFakture sf = stavkaFaktureRepository.getOne(id);
		if (sf == null) {
			return false;
		}
		Faktura faktura = sf.getFaktura();
		if (faktura.getStatusFakture() != StatusFakture.PORUDZBENICA) {
			return false;
		}
		sf.setObrisana(true);
		stavkaFaktureRepository.saveAndFlush(sf);
		//Treba update-ovati i fakturu, jer se na njoj stavka vise ne nalazi?
		//fakturaServiceInterface.update(sf.getFaktura());
		return true;
	}

	@Override
	public void createSfFromSc(StavkaCenovnika stavka) {
		StavkaFakture sf = new StavkaFakture();
		PDV pdv = stavka.getRobaUsluga().getGrupaRobe().getPdv();
		StopaPDV stopa = pdv_serviceInterface.findActiveStopaPdv(pdv.getId());
		sf.setRobaUsluga(stavka.getRobaUsluga());
		//TODO: Trenutno hardkodovano, ako se stigne preko principala
		sf.setFaktura(fakturaServiceInterface.getActiveFakturaForKorisnik(korisnikServiceInterface.findByEmail("mrakita1993@gmail.com")));
		//TODO: Kolicina i rabat se unose na frontu
		sf.setKolicina(1);
		sf.setRabat(0);
		sf.setOsnovicaZaPdv(stavka.getCena() * 1 - 0);
		sf.setIznosPdva(sf.getOsnovicaZaPdv() * stopa.getProcenat()/100);
		sf.setProcenatPdva(stopa.getProcenat());
		sf.setIznosStavke(stavka.getCena());
		sf.setJedinicnaCena(stavka.getCena());
		sf.setObrisana(false);
	}
}
