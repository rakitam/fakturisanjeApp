package sf.posinf.fakturisanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sf.posinf.fakturisanje.model.Mesto;

@Repository
public interface MestoRepository extends JpaRepository<Mesto, Long> {
	
	Mesto findByObrisanoAndId(boolean obrisano, long id);
	
	Page<Mesto> findAllByNazivIgnoreCaseContainsAndObrisano(String naziv, boolean obrisano, Pageable pageable);

}
