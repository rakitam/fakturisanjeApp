package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import sf.posinf.fakturisanje.model.PDV;

public interface PDV_ServiceInterface {

    List<PDV> findAll();
    
    PDV findOne(Long id);
    
    PDV save(PDV pdv);
}
