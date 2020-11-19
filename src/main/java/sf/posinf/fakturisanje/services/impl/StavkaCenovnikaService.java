package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.StavkaCenovnika;
import sf.posinf.fakturisanje.repository.StavkaCenovnikaRepository;
import sf.posinf.fakturisanje.services.interfaces.StavkaCenovnikaServiceInterface;

import java.util.List;

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
    public List<StavkaCenovnika> findAllByRobaUsluga_Id(Long id){
    	return stavkaCenovnikaRepository.findAllByRobaUsluga_Id(id);
    }
}
