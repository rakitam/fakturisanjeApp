package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import sf.posinf.fakturisanje.model.RobaUsluga;
import sf.posinf.fakturisanje.repository.RobaUslugaRepository;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;

public class RobaUslugaService implements RobaUslugaServiceInterface{

	@Autowired
    RobaUslugaRepository robaUslugaRepository;

	// Pageable :))))
    @Override
    public Page<RobaUsluga> findAll(String naziv, int brojStanice, int brojPrikazanih) {
        //return robaUslugaRepository.findAllByGrupaRobe_idAndObrisanoAndNazivRobeUslugeIgnoreCaseContains(
                //false, naziv, new PageRequest(brojStanice, brojPrikazanih));
    	return null;
    }

    @Override
    public RobaUsluga findOne(Long id) {
        return robaUslugaRepository.findById(id).orElse(null);
    }	
    
	// Pageable :)
	 @Override
	public Page<RobaUsluga> findAllByGrupaRobe_id(Long id, String naziv, int brojStanice, int brojPrikazanih) {
		//return robaUslugaRepository.findAllByGrupaRobe_idAndObrisanoAndNazivRobeUslugeIgnoreCaseContains(
    	        //id, false, naziv, new PageRequest(brojStanice, brojPrikazanih));
		 return null;
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
	public void update(RobaUsluga robaUsluga) {
		// TODO Auto-generated method stub
		
	}
}
