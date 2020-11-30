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

	@Override
	public Page<Faktura> findAll(String statusFakture, Pageable pageable) {
		if (statusFakture.equals("")) {
			return fakturaRepository.findAll(pageable);
		} else {
			return fakturaRepository.findAllByStatusFakture(StatusFakture.valueOf(statusFakture), pageable);
		}
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

	// Faktura ne moze da se brise sem ako nije u fazi formiranja!
	// Ukoliko menjamo stavke fakture, pravi se nova faktura, a prethodna se
	// stornira
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
			iznosBezRabata += s.getKolicina() * s.getJedinicnaCena();
			osnovica += s.getOsnovicaZaPdv();
			ukupanPdv += s.getIznosPdva();
			iznosZaPlacanje += s.getIznosStavke();
		}
		faktura.setIznosZaPlacanje(iznosZaPlacanje);
		faktura.setOsnovica(osnovica);
		faktura.setUkupanPdv(ukupanPdv);
		faktura.setIznosBezRabata(iznosBezRabata);
		faktura.setRabat(ukupanRabat);
		save(faktura);
	}
}
