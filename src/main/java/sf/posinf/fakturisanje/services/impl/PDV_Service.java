package sf.posinf.fakturisanje.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sf.posinf.fakturisanje.model.PDV;
import sf.posinf.fakturisanje.repository.PDVRepository;
import sf.posinf.fakturisanje.services.interfaces.PDV_ServiceInterface;

@Service
public class PDV_Service implements PDV_ServiceInterface {

    @Autowired
    PDVRepository pdvRepository;

    @Override
    public List<PDV> findAll() {
        return pdvRepository.findAll();
    }

    @Override
    public PDV findOne(Long id) {
        return pdvRepository.findById(id).orElse(null);
    }

    @Override
    public PDV save(PDV pdv) {
        pdvRepository.save(pdv);
        return pdv;
    }


	
	
	
	
	
}
