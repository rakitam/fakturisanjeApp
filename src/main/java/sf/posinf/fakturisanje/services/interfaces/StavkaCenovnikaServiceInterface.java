package sf.posinf.fakturisanje.services.interfaces;

import java.util.List;

import sf.posinf.fakturisanje.model.RobaUsluga;
import sf.posinf.fakturisanje.model.StavkaCenovnika;

public interface StavkaCenovnikaServiceInterface {

	List<StavkaCenovnika> findAll();

	StavkaCenovnika findOne(Long id);

	StavkaCenovnika save(StavkaCenovnika stavkaCenovnika);

	List<StavkaCenovnika> findAllByRobaUsluga_Id(Long id);

	Boolean delete(Long id);
	
	void update(StavkaCenovnika stavkaCenovnika);
}
