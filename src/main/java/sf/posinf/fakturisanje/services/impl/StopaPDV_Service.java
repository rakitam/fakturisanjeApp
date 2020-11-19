package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.StopaPDV;
import sf.posinf.fakturisanje.repository.StopaPDVRepository;
import sf.posinf.fakturisanje.services.interfaces.StopaPDV_ServiceInterface;

import java.util.List;

@Service
public class StopaPDV_Service implements StopaPDV_ServiceInterface {

    @Autowired
    StopaPDVRepository stopaPdvRepository;

    @Override
    public List<StopaPDV> findAll() {
        return stopaPdvRepository.findAll();
    }

    @Override
    public StopaPDV findOne(Long id) {
        return stopaPdvRepository.findById(id).orElse(null);
    }

    @Override
    public StopaPDV save(StopaPDV stopaPDV) {
        stopaPdvRepository.save(stopaPDV);
        return stopaPDV;
    }
}
