package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.PDV;

import java.util.List;

public interface PDV_ServiceInterface {

    List<PDV> findAll();
    
    PDV findOne(Long id);
    
    PDV save(PDV pdv);
}
