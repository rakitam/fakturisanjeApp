package sf.posinf.fakturisanje.services.impl;

import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.*;
import sf.posinf.fakturisanje.repository.FakturaRepository;
import sf.posinf.fakturisanje.repository.PoslovnaGodinaRepository;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PreduzeceServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StavkaFaktureServiceInterface;

import java.util.Date;
import java.util.List;

@Service
public class FakturaService implements FakturaServiceInterface {

	@Autowired
	FakturaRepository fakturaRepository;

	@Autowired
	PoslovnaGodinaServiceInterface poslovnaGodinaServiceInterface;

	@Autowired
	StavkaFaktureServiceInterface stavkaFaktureServiceInterface;

	@Autowired
	PreduzeceServiceInterface preduzeceServiceInterface;

	@Override
	public Page<Faktura> findAll(String statusFakture, Pageable pageable) {
		if (statusFakture.equals("")) {
			return fakturaRepository.findAll(pageable);
		} else {
			return fakturaRepository.findAllByStatusFakture(StatusFakture.valueOf(statusFakture), pageable);
		}
	}

	@Override
	public Page<Faktura> findAllByKorisnik(long korisnik_id, String statusFakture, Pageable pageable) {
		if (statusFakture.equals("")) {
			return fakturaRepository.findAllByKorisnik_Id(korisnik_id, pageable);
		} else {
			return fakturaRepository.findAllByKorisnik_IdAndStatusFakture(korisnik_id, StatusFakture.valueOf(statusFakture), pageable);
		}
	}

	@Override
	public Faktura findOne(Long id) {
		return fakturaRepository.getOne(id);
	}

	@Override
	public Faktura save(Faktura faktura) {
		faktura.setPreduzece(preduzeceServiceInterface.findOne(1L));
		fakturaRepository.save(faktura);
		return faktura;
	}

	// Faktura moze samo da se stornira
	@Override
	public Boolean storniraj(Faktura faktura) {
		if(faktura.getStatusFakture() != StatusFakture.STORNIRANA) {
			faktura.setStatusFakture(StatusFakture.STORNIRANA);
			faktura.setDatumStorniranja(new Date());
			save(faktura);
			return true;
		}		
		return false;
	}

	@Override
	public Faktura getActiveFakturaForKorisnik(Korisnik korisnik) {
		Faktura faktura = fakturaRepository.findByKorisnik_IdAndStatusFakture(korisnik.getId(), StatusFakture.PORUDZBENICA);
		if(faktura == null) {
			PoslovnaGodina poslednjaPoslovnaGodina = poslovnaGodinaServiceInterface.findByZakljucanaIsFalse();
			faktura = new Faktura();
			faktura.setKorisnik(korisnik);
			faktura.setBrojFakture(poslednjaPoslovnaGodina.getFakture().size() + 1);
			faktura.setStatusFakture(StatusFakture.PORUDZBENICA);
			faktura.setIznosZaPlacanje(0);
			faktura.setOsnovica(0);
			faktura.setUkupanPdv(0);
			faktura.setIznosBezRabata(0);
			faktura.setRabat(0);
			faktura.setPoslovnaGodina(poslednjaPoslovnaGodina);
			faktura = save(faktura);
		}
		return faktura;
	}

	@Override
	public List<Faktura> findAllByPreduzece_IdAndPoslovnaGodina_Godina(long preduzeceId, int godina) {
		return fakturaRepository.findAllByPreduzece_IdAndPoslovnaGodina_Godina(preduzeceId, godina);
	}

	@Override
	public List<Faktura> findAllByPoslovnaGodina_Id(long poslovnaGodinaId) {
		return fakturaRepository.findAllByPoslovnaGodina_Id(poslovnaGodinaId);
	}

	// Faktura ne moze da se brise sem ako nije u fazi formiranja!
	// Ukoliko menjamo stavke fakture, pravi se nova faktura, a prethodna se stornira
	@Override
	public Boolean update(Faktura faktura) {
		Faktura fakturaDB = fakturaRepository.getOne(faktura.getId());
		if (fakturaDB.getStatusFakture() == StatusFakture.FORMIRANA || faktura.getStatusFakture() != StatusFakture.FORMIRANA) {
			fakturaDB.setStatusFakture(faktura.getStatusFakture());
			save(fakturaDB);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void racunaj(Long id) {
		Faktura faktura = findOne(id);
		double osnovica = 0;
		double ukupanPdv = 0;
		double iznosZaPlacanje = 0;
		double iznosBezRabata = 0;
		double ukupanRabat = 0;
		for (StavkaFakture s : stavkaFaktureServiceInterface.findByFaktura_id(faktura.getId())) {
			ukupanRabat += s.getRabat();
			iznosBezRabata += s.getJedinicnaCena() * s.getKolicina();
			osnovica += (s.getKolicina() * s.getJedinicnaCena()) - s.getRabat();
			ukupanPdv += s.getIznosPdva();
			iznosZaPlacanje += s.getOsnovicaZaPdv() + s.getIznosPdva();
		}
		faktura.setIznosZaPlacanje(iznosZaPlacanje);
		faktura.setOsnovica(osnovica);
		faktura.setUkupanPdv(ukupanPdv);
		faktura.setIznosBezRabata(iznosBezRabata);
		faktura.setRabat(ukupanRabat);
		save(faktura);
	}
}
