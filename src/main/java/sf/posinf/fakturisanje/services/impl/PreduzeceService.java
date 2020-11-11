package sf.posinf.fakturisanje.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import sf.posinf.fakturisanje.model.Faktura;
import sf.posinf.fakturisanje.model.Preduzece;
import sf.posinf.fakturisanje.repository.FakturaRepository;
import sf.posinf.fakturisanje.repository.PreduzeceRepository;
import sf.posinf.fakturisanje.services.interfaces.PreduzeceServiceInterface;

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
    public Preduzece save(Preduzece preduzece) {
        preduzeceRepository.save(preduzece);
        return preduzece;
    }

    @Override
    public List<Faktura> findAllByPreduzeceAndVrstaFakture(boolean vrstaFakture,long id) {
        return fakturaRepository.findByVrstaFaktureAndPreduzece_Id(vrstaFakture,id);
    }

    @Override
    public List<Faktura> findAllByPreduzeceAndVrstaFaktureAndPlaceno(boolean vrstaFakture,long id, boolean placeno) {
        return fakturaRepository.findByVrstaFaktureAndPreduzece_IdAndPlaceno(vrstaFakture,id, true);
    }
    
    @Override
    public Boolean delete(Long id) {
        Preduzece preduzece = preduzeceRepository.findById(id).orElse(null);
        preduzece.setObrisano(true);
        preduzeceRepository.saveAndFlush(preduzece);
        return true;
    }
}
