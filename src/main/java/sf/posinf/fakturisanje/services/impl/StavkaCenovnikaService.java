package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.repository.StavkaCenovnikaRepository;
import sf.posinf.fakturisanje.services.interfaces.StavkaCenovnikaServiceInterface;

@Service
public class StavkaCenovnikaService implements StavkaCenovnikaServiceInterface{


    @Autowired
    StavkaCenovnikaRepository stavkaCenovnikaRepository;

	@Override
	public List<StavkaCenovnika> findAll() {
        return stavkaCenovnikaRepository.findAll();
	}

	@Override
	public StavkaCenovnika findOne(Long id) {
        return stavkaCenovnikaRepository.findById(id).orElse(null);
	}

    @Override
    public StavkaCenovnika save(StavkaCenovnika stavkaCenovnika) {
        stavkaCenovnikaRepository.save(stavkaCenovnika);
        return stavkaCenovnika;
    }

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
