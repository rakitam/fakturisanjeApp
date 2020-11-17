package sf.posinf.fakturisanje.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.StavkaFakture;
import sf.posinf.fakturisanje.repository.FakturaRepository;
import sf.posinf.fakturisanje.services.interfaces.FakturaServiceInterface;

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
	public List<Faktura> findAllByPoslovnaGodina(long poslovnaGodina) {
		return fakturaRepository.findAllByPoslovnaGodina_Id(poslovnaGodina);
	}

	@Override
	public List<Faktura> findAllByStatusFakture(String statusFakture) {
		return fakturaRepository.findAllByStatusFakture(statusFakture);
	}
	
	@Override
	public List<Faktura> findAllByPreduzece(String preduzece) {
		return fakturaRepository.findAllByPreduzece(preduzece);
	}

	@Override
	public List<Faktura> findAllByKorisnik_Email(String korisnikEmail) {
		return fakturaRepository.findAllByKorisnik_Email(korisnikEmail);
	}

	@Override
	public List<Faktura> findAllByStatusFaktureAndKorisnik_EmailAndPoslovnaGodina(String statusFakture,
			String korisnikEmail, long poslovnaGodina) {
		return fakturaRepository.findAllByStatusFaktureAndKorisnik_EmailAndPoslovnaGodina_Id
				(statusFakture, korisnikEmail, poslovnaGodina);
	}

	@Override
	public Faktura save(Faktura faktura) {
		fakturaRepository.save(faktura);
        return faktura;
	}

	//TODO: Dodati atribut "obrisana" u entitet "faktura" i zavrsiti metodu
	//Faktura moze da se brise ISKLJUCIVO ako je u fazi formiranja
	@Override
	public Boolean delete(Long id) {
		return null;
	}

	@Override
	public void update(Faktura faktura) {
		double osnovica = 0;
    	double ukupanPdv = 0;
    	double iznosZaPlacanje = 0;
    	double iznosBezRabata = 0;
    	double rabat = 0;
    	for (StavkaFakture s : faktura.getStavkeFakture()) {
    		rabat += s.getRabat();
    		iznosBezRabata += s.getKolicina() * s.getJedinicnaCena();
    		osnovica += s.getOsnovicaZaPdv();
    		ukupanPdv += s.getIznosPdva();
    		iznosZaPlacanje += s.getIznosStavke();
    	}
    	faktura.setIznosZaPlacanje(iznosZaPlacanje);
    	faktura.setOsnovica(osnovica);
    	faktura.setUkupanPdv(ukupanPdv);
    	faktura.setIznosBezRabata(iznosBezRabata);
    	faktura.setRabat(rabat);
    	save(faktura);		
	}
}
