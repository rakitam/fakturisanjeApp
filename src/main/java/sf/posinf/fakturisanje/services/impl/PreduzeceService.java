package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.repository.FakturaRepository;
import sf.posinf.fakturisanje.repository.PreduzeceRepository;
import sf.posinf.fakturisanje.services.interfaces.PreduzeceServiceInterface;

import java.util.List;

@Service
public class PreduzeceService implements PreduzeceServiceInterface {

	@Autowired
    PreduzeceRepository preduzeceRepository;

    @Autowired
    FakturaRepository fakturaRepository;
	
    @Override
    public List<Preduzece> findAll() {
        return preduzeceRepository.findAllByObrisano(false);
    }

    @Override
    public Preduzece findOne(Long id) {
        return preduzeceRepository.findById(id).orElse(null);
    }
    
    @Override
	public List<Faktura> findAllByPreduzeceAndStatusFaktureAndPoslovnaGodina(long preduzece, long poslovnaGodina) {
        if(poslovnaGodina == 0) {
            return fakturaRepository.findAllByPreduzece_Id(preduzece);
        } else {
            return fakturaRepository.findAllByPreduzece_IdAndPoslovnaGodina_Id(preduzece, poslovnaGodina);
        }
	}

	@Override
	public List<Faktura> findAllByPreduzeceAndStatusFaktureAndPlaceno(long id, String statusFakture, boolean placeno) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Preduzece save(Preduzece preduzece) {
        return preduzeceRepository.save(preduzece);
    }    
    
    @Override
    public Boolean delete(Long id) {
        Preduzece preduzece = preduzeceRepository.findById(id).orElse(null);
        preduzece.setObrisano(true);
        preduzeceRepository.saveAndFlush(preduzece);
        return true;
    }
}
