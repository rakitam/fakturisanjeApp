package sf.posinf.fakturisanje.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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

	//TODO: ZASTO SMARA OPTIONAL
	@Override
	public Faktura findOne(Long id) {
		return null;
	}

	@Override
	public List<Faktura> findAllByVrstaFakture(boolean vrstaFakture) {
		return fakturaRepository.findAllByVrstaFakture(vrstaFakture);
	}

	@Override
	public List<Faktura> findAllByVrstaFaktureAndPoslovnaGodina(boolean vrstaFakture, long poslovnaGodina) {
		return fakturaRepository.findAllByVrstaFaktureAndPoslovnaGodina_Id(vrstaFakture, poslovnaGodina);
	}

	@Override
	public List<Faktura> findAllByPoslovniPartner(String nazivPartnera) {
		return fakturaRepository.findAllByPoslovniPartner_NazivPartnera(nazivPartnera);
	}

	@Override
	public List<Faktura> findAllByVrstaFaktureAndPoslovniPartner(boolean vrstaFakture, String poslovniPartner) {
		return fakturaRepository.findAllByVrstaFaktureAndPoslovniPartner_NazivPartnera(vrstaFakture, poslovniPartner);
	}

	@Override
	public List<Faktura> findAllByVrstaFaktureAndPoslovniPartnerAndPoslovnaGodina(boolean vrstaFakture,
			String poslovniPartner, long poslovnaGodina) {
		return fakturaRepository.findAllByVrstaFaktureAndPoslovniPartner_NazivPartneraAndPoslovnaGodina_Id(vrstaFakture, poslovniPartner, poslovnaGodina);
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
