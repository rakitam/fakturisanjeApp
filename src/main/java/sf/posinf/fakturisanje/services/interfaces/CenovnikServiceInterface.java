package sf.posinf.fakturisanje.services.interfaces;

import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import sf.posinf.fakturisanje.model.Cenovnik;
import sf.posinf.fakturisanje.model.StavkaCenovnika;

public interface CenovnikServiceInterface {

	List<Cenovnik> findAll(int brojStranice, int brojPrikazanih);

	List<StavkaCenovnika> findAllByCenovnikId(long id, String nazivRobeUsluge, int brojStranice, int brojPrikazanih);

	Cenovnik findOne(Long id);

	Cenovnik save(Cenovnik cenovnik);

	Boolean delete(Long id);
}
