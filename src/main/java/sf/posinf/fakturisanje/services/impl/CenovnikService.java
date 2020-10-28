package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.repository.CenovnikRepository;
import sf.posinf.fakturisanje.services.interfaces.CenovnikServiceInterface;


public class CenovnikService implements CenovnikServiceInterface{


    @Autowired
    CenovnikRepository cenovnikRepository;
   

	@Override
	public Cenovnik findOne(Long id) {
        return cenovnikRepository.findById(id).orElse(null);
	}

	@Override
	public Cenovnik save(Cenovnik cenovnik) {
		cenovnikRepository.save(cenovnik);
	        return cenovnik;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cenovnik> findAll() {
        return cenovnikRepository.findAll();

	}

}
