package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.PDV;
import sf.posinf.fakturisanje.model.StavkaFakture;
import sf.posinf.fakturisanje.model.StopaPDV;
import sf.posinf.fakturisanje.repository.StopaPDVRepository;
import sf.posinf.fakturisanje.services.interfaces.PDV_ServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.StopaPDV_ServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StopaPDV_Service implements StopaPDV_ServiceInterface {

    @Autowired
    StopaPDVRepository stopaPdvRepository;

    @Autowired
    PDV_ServiceInterface pdvserviceInterface;

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

    @Override
    public List<StopaPDV> findAllByPdv_IdAndActiveIsTrue(long pdvID) {
        PDV pdv = pdvserviceInterface.findOne(pdvID);
        if (pdv == null || pdv.getStopePdv().isEmpty()) {
            System.out.println(pdv);
            return null;
        }
        List<StopaPDV> stope1 = stopaPdvRepository.findAllByPdv_IdAndActiveIsTrue(pdvID);
        if (stope1.isEmpty()) {
            return null;
        }
        return stope1;
        /*List<StopaPDV> stope = new ArrayList<>();
        if (pdv == null || pdv.getStopePdv().isEmpty()) {
            System.out.println(pdv);
            return null;
        }
        List<StopaPDV> stopeZaPdv = new ArrayList(pdv.getStopePdv());

        for (StopaPDV s : stopeZaPdv) {
            if(s.isActive() == true) {
                stope.add(s);
            } else {
                return null;
            }
        }
        return stope;*/
    }
}
