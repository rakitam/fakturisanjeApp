package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.StopaPDV;

import java.util.List;

public interface StopaPDV_ServiceInterface {
	
    List<StopaPDV> findAll();
    
    StopaPDV findOne(Long id);
    
    StopaPDV save(StopaPDV stopaPDV);

    List<StopaPDV> findAllByPdv_IdAndActiveIsTrue(long pdvID);
}
