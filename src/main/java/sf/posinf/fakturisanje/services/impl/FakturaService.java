package sf.posinf.fakturisanje.services.impl;

import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Korisnik;
import sf.posinf.fakturisanje.model.PoslovnaGodina;
import sf.posinf.fakturisanje.model.StatusFakture;
import sf.posinf.fakturisanje.repository.FakturaRepository;
import sf.posinf.fakturisanje.repository.PoslovnaGodinaRepository;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.PoslovnaGodinaServiceInterface;

import java.util.List;

@Service
public class FakturaService implements FakturaServiceInterface {

	@Autowired
	FakturaRepository fakturaRepository;

	@Autowired
	PoslovnaGodinaServiceInterface poslovnaGodinaServiceInterface;

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
	public Boolean storniraj(Faktura faktura) {
		if(faktura.getStatusFakture() != StatusFakture.STORNIRANA) {
			faktura.setStatusFakture(StatusFakture.STORNIRANA);
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
