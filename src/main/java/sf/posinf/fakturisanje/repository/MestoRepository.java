package sf.posinf.fakturisanje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.Mesto;

@Repository
public interface MestoRepository extends JpaRepository<Mesto, Long> {
	
	Mesto findByObrisanoAndId(boolean obrisano, long id);
	
	List<Mesto> findAllByNazivIgnoreCaseContainsAndObrisano(String naziv, boolean obrisano);

}
