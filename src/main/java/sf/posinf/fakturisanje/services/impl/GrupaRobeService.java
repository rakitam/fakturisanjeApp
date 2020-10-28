package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import sf.posinf.fakturisanje.model.GrupaRobe;
import sf.posinf.fakturisanje.repository.GrupaRobeRepository;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;

public class GrupaRobeService implements GrupaRobeServiceInterface{

	
	@Autowired
	GrupaRobeRepository grupaRobeRepository;
	
	@Override
    public List<GrupaRobe> findAll(String naziv) {
		return grupaRobeRepository.findAllByNazivGrupeIgnoreCaseContains(naziv);
    }

    @Override
    public GrupaRobe findOne(Long id) {
        return grupaRobeRepository.findById(id).orElse(null);
    }

    @Override
    public GrupaRobe save(GrupaRobe grupaRobe) {
        grupaRobeRepository.save(grupaRobe);
        return grupaRobe;
    }

    @Override
    public Boolean delete(Long id) {      
    	grupaRobeRepository.deleteById(id);;
        return true;
    }
    
    @Override
    public List<GrupaRobe> findByPreduzece_id(Long id){
    	return grupaRobeRepository.findAllByPreduzece_id(id);
    }
	
}
