package sf.posinf.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.model.Mesto;

@Repository
public interface MestoRepository extends JpaRepository<Mesto, Long> {
	
	Mesto findByObrisanoAndId(boolean obrisano, long id);
	
	Page<Mesto> findAllByNazivIgnoreCaseContainsAndObrisano(
			String naziv, boolean obrisano, Pageable pageable);

}
