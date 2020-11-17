package sf.posinf.fakturisanje.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sf.posinf.fakturisanje.model.RobaUsluga;

@Repository
public interface RobaUslugaRepository extends JpaRepository<RobaUsluga,Long>{

	List<RobaUsluga> findAllByNazivRobeUslugeIgnoreCaseContains(String nazivRobeUsluge);
	
	Page<RobaUsluga> findAllByGrupaRobe_idAndObrisanoAndNazivRobeUslugeIgnoreCaseContains(Long id,
            boolean obrisano, String nazivRobeUsluge, Pageable pageable);
	
}
