package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sf.posinf.fakturisanje.model.RobaUsluga;
import sf.posinf.fakturisanje.repository.RobaUslugaRepository;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;

public class RobaUslugaService implements RobaUslugaServiceInterface{

	@Autowired
    RobaUslugaRepository robaUslugaRepository;

    @Override
    public List<RobaUsluga> findAll(String naziv) {
        return robaUslugaRepository.findAllByNazivRobeUslugeIgnoreCaseContains(naziv);
    }

    @Override
    public RobaUsluga findOne(Long id) {
        return robaUslugaRepository.findById(id).orElse(null);
    }

    @Override
    public RobaUsluga save(RobaUsluga robaUsluga) {
        robaUslugaRepository.save(robaUsluga);
        return robaUsluga;
    }

    @Override
    public Boolean delete(Long id) {
        robaUslugaRepository.deleteById(id);
        return true;
    }
    
    @Override
    public List<RobaUsluga> findAllByGrupaRobe_id(Long id, String naziv){
    	return robaUslugaRepository.findAllByGrupaRobe_idAndNazivRobeUslugeIgnoreCaseContains(id, naziv);
    }
	
}
