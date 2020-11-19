package sf.posinf.fakturisanje.services.interfaces;

import sf.posinf.fakturisanje.model.StavkaCenovnika;

import java.util.List;

public interface StavkaCenovnikaServiceInterface {

	List<StavkaCenovnika> findAll();

	StavkaCenovnika findOne(Long id);

	StavkaCenovnika save(StavkaCenovnika stavkaCenovnika);

	List<StavkaCenovnika> findAllByRobaUsluga_Id(Long id);

	Boolean delete(Long id);
	
	void update(StavkaCenovnika stavkaCenovnika);
}
