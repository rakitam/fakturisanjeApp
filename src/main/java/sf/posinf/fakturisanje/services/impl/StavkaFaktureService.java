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
		return stavkaFaktureRepository.findByFaktura_idAndObrisanaIsFalse(id);
	}

	@Override
	public StavkaFakture findOne(Long id) {
		return stavkaFaktureRepository.getOne(id);
	}

	//Popraviti u zavisnosti od toga da li se cuva ili menja
	@Override
	public StavkaFakture save(StavkaFakture stavkaFakture) {
		Faktura faktura = stavkaFakture.getFaktura();
		if (faktura.getStatusFakture() != StatusFakture.PORUDZBENICA && faktura.getStatusFakture() != StatusFakture.PORUDZBENICA_ADMIN) {
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
		if (faktura.getStatusFakture() != StatusFakture.PORUDZBENICA && faktura.getStatusFakture() != StatusFakture.PORUDZBENICA_ADMIN) {
			return false;
		}
		sf.setObrisana(true);
		stavkaFaktureRepository.saveAndFlush(sf);
		fakturaServiceInterface.racunaj(faktura.getId());
		return true;
	}

	@Override
	public void createSfFromSc(StavkaCenovnika stavka, int kolicina, int rabat, String email, boolean admin) {
		StavkaFakture sf = new StavkaFakture();
		PDV pdv = stavka.getRobaUsluga().getGrupaRobe().getPdv();
		StopaPDV stopa = pdv_serviceInterface.findActiveStopaPdv(pdv.getId());
		Faktura korpa = fakturaServiceInterface.getActiveFakturaForKorisnik(korisnikServiceInterface.findByEmail(email), admin);
		sf.setRobaUsluga(stavka.getRobaUsluga());
		sf.setFaktura(korpa);
		sf.setKolicina(kolicina);
		sf.setRabat(rabat);
		sf.setOsnovicaZaPdv(stavka.getCena() * kolicina - rabat);
		sf.setIznosPdva(sf.getOsnovicaZaPdv() * stopa.getProcenat()/100);
		sf.setProcenatPdva(stopa.getProcenat());
		sf.setJedinicnaCena(stavka.getCena());
		sf.setIznosStavke(stavka.getCena() * kolicina);
		sf.setObrisana(false);
		save(sf);

		fakturaServiceInterface.racunaj(korpa.getId());
	}


}
