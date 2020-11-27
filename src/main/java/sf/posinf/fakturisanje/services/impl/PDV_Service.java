package sf.posinf.fakturisanje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sf.posinf.fakturisanje.model.PDV;
import sf.posinf.fakturisanje.model.StopaPDV;
import sf.posinf.fakturisanje.repository.PDVRepository;
import sf.posinf.fakturisanje.services.interfaces.PDV_ServiceInterface;
import sf.posinf.fakturisanje.services.interfaces.RobaUslugaServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public StopaPDV findActiveStopaPdv(long pdvID) {
        PDV pdv = findOne(pdvID);
        if (pdv == null || pdv.getStopePdv().isEmpty()) {
            return null;
        }
        List<StopaPDV> stope = new ArrayList(pdv.getStopePdv());

        // Sortiramo stopePDV-a po datumu vazenja
        Collections.sort(stope, (stopaPdv1, stopaPdv2) -> (stopaPdv1.getDatumVazenja().compareTo(stopaPdv2.getDatumVazenja())));

        // vracamo trenutnu stopu, samo nam je ta potrebna
        return stope.get(stope.size() - 1);
    }


}
