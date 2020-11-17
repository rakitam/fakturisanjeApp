package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import sf.posinf.fakturisanje.model.StopaPDV;

public interface StopaPDV_ServiceInterface {
	
    List<StopaPDV> findAll();
    
    StopaPDV findOne(Long id);
    
    StopaPDV save(StopaPDV stopaPDV);
    
    void update(StopaPDV stopaPDV);

}
