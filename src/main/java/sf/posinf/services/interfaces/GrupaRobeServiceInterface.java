package sf.posinf.services.interfaces;

import java.util.List;

import sf.posinf.model.GrupaRobe;

public interface GrupaRobeServiceInterface {
	
	List<GrupaRobe> findAll(String naziv, int brojStranice, int brojPrikazanih);
    GrupaRobe findOne(Long id);
    List<GrupaRobe> findByPreduzece_id(Long id);
    GrupaRobe save(GrupaRobe grupaRobe);
    Boolean delete(Long id);
	
	
}
