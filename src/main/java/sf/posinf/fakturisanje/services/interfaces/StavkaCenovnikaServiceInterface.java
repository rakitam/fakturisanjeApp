package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sf.posinf.fakturisanje.model.StavkaCenovnika;

import java.util.List;

public interface StavkaCenovnikaServiceInterface {

	Page<StavkaCenovnika> findAll(Pageable pageable);

	StavkaCenovnika findOne(Long id);

	StavkaCenovnika save(StavkaCenovnika stavkaCenovnika);

	List<StavkaCenovnika> findAllByRobaUsluga_Id(Long id);
}
