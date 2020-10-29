package sf.posinf.fakturisanje.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.Cenovnik;

@Repository
public interface CenovnikRepository extends JpaRepository<Cenovnik, Long> {
	
	List<Cenovnik> findAllByObrisano(boolean obrisano);

	Page<Cenovnik> findAllByObrisano(boolean obrisano, Pageable pageable);

	Cenovnik findByObrisanoAndId(boolean obrisano, long id);
}
