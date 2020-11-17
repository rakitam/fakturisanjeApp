package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.GrupaRobe;
import sf.posinf.fakturisanje.repository.GrupaRobeRepository;
import sf.posinf.fakturisanje.services.interfaces.GrupaRobeServiceInterface;

public class GrupaRobeService implements GrupaRobeServiceInterface{

	
	@Autowired
	GrupaRobeRepository grupaRobeRepository;
	
	//TODO: Pageable
	@Override
	public Page<GrupaRobe> findAll(String naziv, int brojStranice, int brojPrikazanih) {
		//return grupaRobeRepository.findAllByObrisanoAndNazivGrupeIgnoreCaseContains(false, naziv,
                //new PageRequest(brojStranice,brojPrikazanih));
		return null;
	}

	@Override
	public GrupaRobe findOne(Long id) {
		return grupaRobeRepository.getOne(id);
	}

	@Override
	public List<GrupaRobe> findByPreduzece_id(Long id) {
		return grupaRobeRepository.findAllByPreduzece_id(id);
	}

	@Override
	public GrupaRobe save(GrupaRobe grupaRobe) {
		return grupaRobeRepository.save(grupaRobe);
	}

	@Override
	public void update(GrupaRobe grupaRobe) {
		// TODO Auto-generated method stub
		
	}
}
