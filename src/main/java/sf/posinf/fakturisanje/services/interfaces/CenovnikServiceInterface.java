package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.StavkaCenovnika;

public interface CenovnikServiceInterface {

	Page<Cenovnik> findAll(Pageable pageable);

	Page<StavkaCenovnika> findAllByCenovnikId(long id, String nazivRobeUsluge, Pageable pageable);

	Cenovnik findOne(Long id);

	Cenovnik save(Cenovnik cenovnik);

	Boolean proglasiNeaktivnim(Cenovnik cenovnik);
}
