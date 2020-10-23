package sf.posinf.services.interfaces;

import java.util.List;

import sf.posinf.model.StopaPDV;

public interface StopaPDV_ServiceInterface {
	
    List<StopaPDV> findAll();
    StopaPDV findOne(Long id);
    StopaPDV save(StopaPDV stopaPDV);

}
